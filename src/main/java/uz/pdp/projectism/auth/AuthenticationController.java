package uz.pdp.projectism.auth;

import org.springframework.web.bind.annotation.*;
import uz.pdp.projectism.constants.AppConstants;
import uz.pdp.projectism.payload.ApiResponse;
import uz.pdp.projectism.payload.LoginDTO;
import uz.pdp.projectism.payload.RegisterDTO;

@RequestMapping(AuthenticationController.AUTH_API)
public interface AuthenticationController {
    String AUTH_API = AppConstants.BASE_PATH+"/auth";

    @PostMapping("/register")
    public ApiResponse<AuthenticationResponse> register(
            @RequestBody RegisterDTO registerDTO
            );

    @PostMapping("/login")
    public ApiResponse<AuthenticationResponse> login(
             @RequestBody LoginDTO loginDTO
             );

    @GetMapping("/verifyEmail")
    public ApiResponse<?> confirmEmail(@RequestParam String emailCode,@RequestParam String email);


//    api/auth/verifyEmail?emailCode='1code1&email=nodirbekdadakuziyev@gmail.com


}
