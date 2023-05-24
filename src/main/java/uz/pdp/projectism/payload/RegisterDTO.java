package uz.pdp.projectism.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RegisterDTO {
    private String firstname;
    private String lastname;
    private String email;
    private String password;


//    @NotBlank(message = "{MOST_NOT_BE_PHONE_NUMBER_EMPTY}")
//    @Pattern(regexp = "\\+998[0-9]{9}",message = "{WRONG_PHONE_NUMBER_FORMAT}")
//    private String phoneNumber;
//    @NotBlank
//    private String verifyCode;
}
