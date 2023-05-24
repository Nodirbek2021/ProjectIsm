package uz.pdp.projectism.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.projectism.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);
}
