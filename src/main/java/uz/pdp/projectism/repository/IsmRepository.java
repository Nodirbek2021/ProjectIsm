package uz.pdp.projectism.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.projectism.entity.Ism;

import java.util.UUID;

public interface IsmRepository extends JpaRepository<Ism, Long> {
}
