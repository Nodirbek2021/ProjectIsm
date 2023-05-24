package uz.pdp.projectism.auth;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.projectism.payload.ApiResponse;
import uz.pdp.projectism.payload.LoginDTO;
import uz.pdp.projectism.payload.RegisterDTO;
import uz.pdp.projectism.service.AuthService;

@RestController
@RequiredArgsConstructor
public class AuthenticationControllerImpl implements AuthenticationController{
    private final AuthService authService;
    @Override
    public ApiResponse<AuthenticationResponse> register(RegisterDTO registerDTO) {
        return authService.register(registerDTO);
    }

    @Override
    public ApiResponse<AuthenticationResponse> login(LoginDTO loginDTO) {
        return authService.login(loginDTO);
    }

    @Override
    public ApiResponse<?> confirmEmail(String emailCode, String email) {
        return authService.confirmEmail(emailCode,email);
    }
}
