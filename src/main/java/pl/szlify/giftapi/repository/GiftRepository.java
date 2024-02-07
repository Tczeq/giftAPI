package pl.szlify.giftapi.repository;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import pl.szlify.giftapi.model.Gift;

import java.util.List;
import java.util.Optional;

public interface GiftRepository extends JpaRepository<Gift, Integer> {

    List<Gift> findByKidId(int id);

    @Query("SELECT g FROM Gift g WHERE g.id = :giftId AND g.kid.id = :kidId")
    Optional<Gift> findByIdAndKidId(int giftId, int kidId);

    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    Optional<Gift> findWithLockingById(int id);
}
