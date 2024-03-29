package pl.szlify.giftapi.repository;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import pl.szlify.giftapi.model.Kid;

import java.util.Optional;

public interface KidRepository extends JpaRepository<Kid, Integer> {

    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    Optional<Kid> findWithLockingById(int id);
}
