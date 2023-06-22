package uz.pdp.projectism.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import uz.pdp.projectism.constants.AppConstants;
import uz.pdp.projectism.payload.ApiResponse;
import uz.pdp.projectism.payload.LoginDTO;
import uz.pdp.projectism.payload.RegisterDTO;

@RequestMapping(AuthenticationController.AUTH_API)
@Tag(name = "Authentication")
public interface AuthenticationController {
    String AUTH_API = AppConstants.BASE_PATH+"/auth";

    @PostMapping("/register")
    @Operation(summary = "Register")
    public ApiResponse<AuthenticationResponse> register(
            @RequestBody RegisterDTO registerDTO
            );

    @PostMapping("/login")
    @Operation(summary = "Login")
    public ApiResponse<AuthenticationResponse> login(
             @RequestBody LoginDTO loginDTO
             );

    @GetMapping("/verifyEmail")
    @Operation(summary = "Email activation.")
    public ApiResponse<?> confirmEmail(@RequestParam String emailCode,@RequestParam String email);


//    api/auth/verifyEmail?emailCode='1code1&email=nodirbekdadakuziyev@gmail.com


}
