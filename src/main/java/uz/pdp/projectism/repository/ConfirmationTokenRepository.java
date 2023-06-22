package uz.pdp.projectism.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.projectism.entity.ConfirmationToken;
import uz.pdp.projectism.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, UUID> {
    Optional<ConfirmationToken> findByToken(String token);
    Optional<ConfirmationToken>  findByUser(User user);

}
