package uz.pdp.projectism.payload;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class IsmDTO {
    @NotBlank
    private String nameLat;
    @NotBlank
    private String nameCyr;
    private boolean gender;
    @NotBlank
    private String definition;
    @NotBlank
    private String namedPeople ;

    private Integer likeCount ;
    @NotBlank
    private String comingLang;
}
