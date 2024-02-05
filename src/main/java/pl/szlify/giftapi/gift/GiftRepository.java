package pl.szlify.giftapi.gift;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GiftRepository extends JpaRepository<Gift, Integer> {
}
