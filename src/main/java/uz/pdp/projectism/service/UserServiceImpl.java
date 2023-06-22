package uz.pdp.projectism.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import uz.pdp.projectism.entity.ConfirmationToken;
import uz.pdp.projectism.entity.Role;
import uz.pdp.projectism.entity.User;
import uz.pdp.projectism.entity.enums.RoleType;
import uz.pdp.projectism.exceptions.RestException;
import uz.pdp.projectism.payload.ApiResponse;
import uz.pdp.projectism.payload.UserDTO;
import uz.pdp.projectism.repository.ConfirmationTokenRepository;
import uz.pdp.projectism.repository.RoleRepository;
import uz.pdp.projectism.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final JavaMailSender javaMailSender;
//    private final ConfirmationTokenService confirmationTokenService;




    @Override
    public ApiResponse<?> addUser(UserDTO userDTO) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean enabled = principal.isEnabled();
        if (!enabled){
            return ApiResponse.errorResponse(RestException.unauthorized("Please activate your Email!")
                    .getMessage());
        }

        System.out.println(principal);
        Role currentRole = principal.getRole();
        Role role = roleRepository.findById(userDTO.getRoleId()).orElseThrow(() -> RestException.notFound("Role not found!"));
        if (currentRole.getRoleType().equals(RoleType.ADMIN) &&
                (role.getRoleType().equals(RoleType.ADMIN)||role.getRoleType().equals(RoleType.DIRECTOR) ))
            return ApiResponse.errorResponse(RestException.forbidden());

//        Role roleByDto = roleRepository.findById(userDTO.getRoleId()).orElseThrow(() -> RestException.notFound("Role Not Found"));
        User user = new User(
                userDTO.getFirstname(),
                userDTO.getLastname(),
                userDTO.getEmail(),
                passwordEncoder.encode(userDTO.getPassword()),
                role,
                false
        );
        User savedUser = userRepository.save(user);
        ConfirmationToken confirmationToken=new ConfirmationToken();
        confirmationToken.setToken(UUID.randomUUID().toString());
        confirmationToken.setCreatedAt(LocalDateTime.now());
        confirmationToken.setExpiresAt(LocalDateTime.now().plusMinutes(15));
        confirmationToken.setUser(savedUser);

        ConfirmationToken savedConfirmationToken = confirmationTokenRepository.save(confirmationToken);

        Boolean aBoolean = sendEmail(user.getEmail(), savedConfirmationToken.getToken());
        System.out.println("=============================================="+aBoolean);

        return ApiResponse.successResponse(savedUser, "Successfully saved!");
    }

    @Override
    public ApiResponse<?> getById(UUID id) {
//        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        System.out.println(principal);
//        Role role = principal.getRole();
//        if (!role.getRoleType().equals(RoleType.DIRECTOR))
//            return ApiResponse.errorResponse(RestException.forbidden());
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean enabled = principal.isEnabled();
        if (!enabled){
            return ApiResponse.errorResponse(RestException.unauthorized("Please activate your Email!")
                    .getMessage());
        }
        User userById = userRepository.findById(id).orElseThrow(() -> RestException.notFound("User not Found"));
        return ApiResponse.successResponse(userById, "Done!");
    }

    @Override
    public ApiResponse<?> getAllUsers() {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean enabled = principal.isEnabled();
        if (!enabled){
            return ApiResponse.errorResponse(RestException.unauthorized("Please activate your Email!")
                    .getMessage());
        }

        List<User> allUsers = userRepository.findAll();
        return ApiResponse.successResponse(allUsers, "Done!");
    }

    @Override
    public ApiResponse<?> editUser(UUID id, UserDTO userDTO) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean enabled = principal.isEnabled();
        if (!enabled){
            return ApiResponse.errorResponse(RestException.unauthorized("Please activate your Email!")
                    .getMessage());
        }


        System.out.println(principal);
        Role currentRole = principal.getRole();
        Role role = roleRepository.findById(userDTO.getRoleId()).orElseThrow(() -> RestException.notFound("Role not found!"));
        if (currentRole.getRoleType().equals(RoleType.ADMIN) &&
                (role.getRoleType().equals(RoleType.ADMIN)||role.getRoleType().equals(RoleType.DIRECTOR) ))
            return ApiResponse.errorResponse(RestException.forbidden());


        User userById = userRepository.findById(id).orElseThrow(() -> RestException.notFound("UserNotFound"));
        userById.setFirstname(userDTO.getFirstname());
        userById.setLastname(userDTO.getLastname());
        userById.setRole(role);
        userById.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User save = userRepository.save(userById);
        return ApiResponse.successResponse(save, "Edited!");
    }

    @Override
    public ApiResponse<?> deleteUser(UUID id) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean enabled = currentUser.isEnabled();
        if (!enabled){
            return ApiResponse.errorResponse(RestException.unauthorized("Please activate your Email!")
                    .getMessage());
        }

        Role currentUserRole = currentUser.getRole();
        User user = userRepository.findById(id).orElseThrow(() -> RestException.notFound("Not found!"));
        if (currentUserRole.getRoleType().equals(RoleType.ADMIN)
                && user.getRole().getRoleType().equals(RoleType.DIRECTOR) ){
            return ApiResponse.errorResponse(RestException.forbidden());
        }

        ConfirmationToken confirmationToken = confirmationTokenRepository.findByUser(user).orElseThrow(() -> RestException.notFound("ConfirmationToken not found"));
        confirmationTokenRepository.delete(confirmationToken);
        userRepository.delete(user);
        return ApiResponse.successResponse("Deleted!");
    }

    public Boolean sendEmail(String sendingEmail , String emailCode){
        try {
            SimpleMailMessage mailMessage =new SimpleMailMessage();
            mailMessage.setFrom("nodirbekdadaqoziyev@gmail.com");
            mailMessage.setTo(sendingEmail);
            mailMessage.setSubject("Assolomu alaykum ! Akkountni tasdiqlash");
            mailMessage.setText("<a href= 'http://localhost:8083/api/auth/verifyEmail?emailCode="
                    +emailCode+"&email="+sendingEmail+ "'>Tasdiqlang</a>");
            javaMailSender.send(mailMessage);
            return true ;
        }catch (Exception e ){
            return false;
        }
    }


}
