package uz.pdp.projectism.service;


//import static jdk.internal.org.jline.reader.impl.LineReaderImpl.CompletionType.List;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.projectism.auth.AuthenticationResponse;
import uz.pdp.projectism.entity.ConfirmationToken;
import uz.pdp.projectism.entity.Role;
import uz.pdp.projectism.entity.User;
import uz.pdp.projectism.entity.enums.RoleType;
import uz.pdp.projectism.repository.ConfirmationTokenRepository;
import uz.pdp.projectism.repository.RoleRepository;
import uz.pdp.projectism.repository.UserRepository;
import uz.pdp.projectism.exceptions.RestException;
import uz.pdp.projectism.filter.JwtService;
import uz.pdp.projectism.payload.ApiResponse;
import uz.pdp.projectism.payload.LoginDTO;
import uz.pdp.projectism.payload.RegisterDTO;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final JavaMailSender javaMailSender;
    private final ConfirmationTokenRepository confirmationTokenRepository;

    @Override
    public ApiResponse<AuthenticationResponse> register(RegisterDTO registerDTO) {
        Role roleByType = roleRepository.findFirstByRoleType(RoleType.USER).orElseThrow(() -> RestException.notFound("RoleNotFound"));
        if (userRepository.existsByEmail(registerDTO.getEmail())){
            return ApiResponse.errorResponse(RestException.conflict("Email already used.").getMessage());
        }
        var user = User.builder()
                .firstname(registerDTO.getFirstname())
                .lastname(registerDTO.getLastname())
                .email(registerDTO.getEmail())
                .password(passwordEncoder.encode(registerDTO.getPassword()))
                .role(roleByType)
                .build();
        User savedUser = userRepository.save(user);
        ConfirmationToken confirmationToken=new ConfirmationToken();
        confirmationToken.setToken(UUID.randomUUID().toString());
        confirmationToken.setCreatedAt(LocalDateTime.now());
        confirmationToken.setExpiresAt(LocalDateTime.now().plusMinutes(15));
        confirmationToken.setUser(savedUser);

        ConfirmationToken savedConfirmationToken = confirmationTokenRepository.save(confirmationToken);

        Boolean aBoolean = sendEmail(user.getEmail(), savedConfirmationToken.getToken());
        System.out.println("=============================================="+aBoolean);


        var jwtToken=jwtService.generateToken(user);
        AuthenticationResponse build = AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

        return ApiResponse.successResponse(build,"Registered!");
    }

    @Override
    public ApiResponse<AuthenticationResponse> login(LoginDTO loginDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getEmail(),
                        loginDTO.getPassword()
                )
        );
        var user = userRepository.findByEmail(loginDTO.getEmail()).orElseThrow(() -> RestException.notFound("NotFound"));

        var jwtToken=jwtService.generateToken(user);
        AuthenticationResponse build = AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

        return ApiResponse.successResponse(build,"Successfully login!");
    }

    @Override
    public ApiResponse<?> confirmEmail(String emailCode, String email) {
        ConfirmationToken confirmationToken = confirmationTokenRepository.findByToken(emailCode).orElseThrow(() -> RestException.notFound("Token Not found!"));
        if (emailCode.equals(confirmationToken.getToken())){
            User user = confirmationToken.getUser();
            user.setEnabled(true);
            User saved = userRepository.save(user);
            return ApiResponse.successResponse("Account activated!");
        }
        return ApiResponse.errorResponse("Oops. Something went wrong! Try again.");
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
