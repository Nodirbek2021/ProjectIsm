package uz.pdp.projectism.service;


import org.springframework.http.ResponseEntity;
import uz.pdp.projectism.auth.AuthenticationResponse;
import uz.pdp.projectism.entity.User;
import uz.pdp.projectism.payload.ApiResponse;
import uz.pdp.projectism.payload.LoginDTO;
import uz.pdp.projectism.payload.RegisterDTO;

public interface AuthService {
    public ApiResponse<AuthenticationResponse> register(
             RegisterDTO registerDTO
    );

    public ApiResponse<AuthenticationResponse> login(
             LoginDTO loginDTO
    );


    ApiResponse<?> confirmEmail(String emailCode,String email);
}
