package uz.pdp.projectism.repository;

import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.projectism.entity.Ism;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface IsmRepository extends JpaRepository<Ism, UUID> {
    List<Ism>findByUpdatedAt(Date date);
    List<Ism>findAllByUpdatedAtAfter(Date date);
}
