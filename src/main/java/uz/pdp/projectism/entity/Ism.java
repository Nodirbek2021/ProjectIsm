package uz.pdp.projectism.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.projectism.entity.template.AbsEntity;
import uz.pdp.projectism.entity.template.AbsEntityLong;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Ism extends AbsEntityLong {
    @Column(name = "nameLat",columnDefinition = "text")
    private String nameLat;
    @Column(name = "nameCry",columnDefinition = "text")
    private String nameCyr;
    @Column(name = "gender",columnDefinition = "text")
    private boolean gender;
    @Column(name = "definition", columnDefinition = "text")
    private String definition;
    @Column(name = "namedPeople", columnDefinition = "text")
    private String namedPeople ;
    @Column(name = "likeCount", columnDefinition = "text")
    private Integer likeCount ;
    @Column(name = "comingLang", columnDefinition = "text")
    private String comingLang;
}
