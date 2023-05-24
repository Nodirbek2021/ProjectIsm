package uz.pdp.projectism.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import uz.pdp.projectism.payload.ApiResponse;

@Service("emailSenderService")
public class EmailService {
//
    private JavaMailSender javaMailSender;
//
//    @Autowired
//    public EmailSenderService(JavaMailSender javaMailSender) {
//        this.javaMailSender = javaMailSender;
//    }
//
//    @Async
//    public void sendEmail(SimpleMailMessage email) {
//        javaMailSender.send(email);
//    }


//    public ApiResponse registerUser(RegisterDto registerDto){
//        boolean existsByEmail = userRepo.existsByEmail(registerDto.getEmail());
//        if (existsByEmail){
//            return new ApiResponse("Bunday email mavjud ",false);
//        }
//        User user = new User();
//        user.setFirstName(registerDto.getFirstName());
//        user.setLastName(registerDto.getLastName());
//        user.setEmail(registerDto.getEmail());
//        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
//        user.setRoles(Collections.singleton(roleRepo.findByRoleName(RoleName.ROLE_USER)));
//        user.setEmailCode(UUID.randomUUID().toString());
//        userRepo.save(user);
//        //Email sending
//        sendEmail(user.getEmail(), user.getEmailCode());
//        return new ApiResponse("Muvaffaqiyatli ro'yhatdan o'tdingiz ! Emaildan tasdiqlang ",true);
//    }


}
