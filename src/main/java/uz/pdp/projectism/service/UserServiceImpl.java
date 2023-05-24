package uz.pdp.projectism.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.projectism.entity.ConfirmationToken;
import uz.pdp.projectism.entity.Role;
import uz.pdp.projectism.entity.User;
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
        Role roleByDto = roleRepository.findById(userDTO.getRoleId()).orElseThrow(() -> RestException.notFound("Role Not Found"));
        User user = new User(
                userDTO.getFirstname(),
                userDTO.getLastname(),
                userDTO.getEmail(),
                passwordEncoder.encode(userDTO.getPassword()),
                roleByDto,
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
        User userById = userRepository.findById(id).orElseThrow(() -> RestException.notFound("User not Found"));
        return ApiResponse.successResponse(userById, "Done!");
    }

    @Override
    public ApiResponse<?> getAllUsers() {
        List<User> allUsers = userRepository.findAll();
        return ApiResponse.successResponse(allUsers, "Done!");
    }

    @Override
    public ApiResponse<?> editUser(UUID id, UserDTO userDTO) {
        User userById = userRepository.findById(id).orElseThrow(() -> RestException.notFound("UserNotFound"));
        userById.setFirstname(userDTO.getFirstname());
        userById.setLastname(userDTO.getLastname());
        Role role = roleRepository.findById(userDTO.getRoleId()).orElseThrow(() -> RestException.notFound("Role not found!"));
        userById.setRole(role);
        userById.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User save = userRepository.save(userById);
        return ApiResponse.successResponse(save, "Edited!");
    }

    @Override
    public ApiResponse<?> deleteUser(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> RestException.notFound("Not found!"));
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
