package uz.pdp.projectism.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.projectism.entity.enums.RoleType;
import uz.pdp.projectism.entity.template.AbsEntity;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
//@Builder
public class Role extends AbsEntity {
    private String name;
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

}
