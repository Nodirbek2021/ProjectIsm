package uz.pdp.projectism.entity;


import jakarta.persistence.*;
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
@Table(name = "roles")

public class Role extends AbsEntity {
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "role_type")
    private RoleType roleType;

}
