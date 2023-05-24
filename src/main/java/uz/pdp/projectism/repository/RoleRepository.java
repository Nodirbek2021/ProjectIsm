package uz.pdp.projectism.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.projectism.entity.Role;
import uz.pdp.projectism.entity.enums.RoleType;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findByRoleType(RoleType roleType);
}
