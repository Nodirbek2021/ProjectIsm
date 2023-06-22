package uz.pdp.projectism.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RegisterDTO {
    @NotBlank
    private String firstname;
    @NotBlank
    private String lastname;
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$")
    private String email;
    @NotBlank
    private String password;


//    @NotBlank(message = "{MOST_NOT_BE_PHONE_NUMBER_EMPTY}")
//    @Pattern(regexp = "\\+998[0-9]{9}",message = "{WRONG_PHONE_NUMBER_FORMAT}")
//    private String phoneNumber;
//    @NotBlank
//    private String verifyCode;
}
