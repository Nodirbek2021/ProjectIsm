package uz.pdp.projectism.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.projectism.entity.ConfirmationToken;
import uz.pdp.projectism.entity.User;

import java.util.Optional;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken,Long> {
    Optional<ConfirmationToken> findByToken(String token);
    Optional<ConfirmationToken>  findByUser(User user);

}
