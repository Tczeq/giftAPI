package pl.szlify.giftapi.gift;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.szlify.giftapi.gift.exception.GiftNotFoundException;
import pl.szlify.giftapi.gift.exception.TooManyGiftsException;
import pl.szlify.giftapi.gift.model.Gift;
import pl.szlify.giftapi.gift.model.command.CreateGiftCommand;
import pl.szlify.giftapi.gift.model.command.UpdateGiftCommand;
import pl.szlify.giftapi.gift.model.dto.GiftDto;
import pl.szlify.giftapi.kid.KidRepository;
import pl.szlify.giftapi.kid.exception.KidNotFoundException;
import pl.szlify.giftapi.kid.model.Kid;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GiftService {

    private final GiftRepository giftRepository;
    private final KidRepository kidRepository;

    public List<GiftDto> findAll() {
        return giftRepository.findAll().stream()
                .map(GiftDto::fromEntity)
                .toList();
    }

    public List<GiftDto> findByKidId(int id) {
        return giftRepository.findByKidId(id).stream()
                .map(GiftDto::fromEntity)
                .toList();
    }

    public GiftDto findByIdAndByKidId(int giftId, int kidId) {
        return giftRepository.findByIdAndKidId(giftId, kidId)
                .map(GiftDto::fromEntity)
                .orElseThrow(() -> new GiftNotFoundException(giftId));
    }

    @Transactional
    public GiftDto addGiftToKidId(CreateGiftCommand command) {
        Gift gift = command.toEntity();
        if(command.getKidId() == null) {
            throw new IllegalArgumentException("kidId cannot be null");
        }
        Kid kid = kidRepository.findWithLockingById(command.getKidId())
                .orElseThrow(() -> new KidNotFoundException(command.getKidId()));

        if(kid.getGifts().size() >= 3) {
            throw new TooManyGiftsException("3 gifts is enough for him");
        }

        gift.setKid(kid);
        kid.getGifts().add(gift);
        giftRepository.save(gift);

        return GiftDto.fromEntity(giftRepository.save(gift));
    }

    @Transactional
    public GiftDto update(int kidId, int giftId, UpdateGiftCommand command) {
        Gift gift = giftRepository.findById(giftId)
                .orElseThrow(() -> new GiftNotFoundException(kidId));
        if(gift.getKid().getId() != kidId) {
            throw new GiftNotFoundException(kidId);
        }

        if (command.getName() != null) {
            gift.setName(command.getName());
        }
        if (command.getPrice() != null) {
            gift.setPrice(command.getPrice());
        }
        giftRepository.save(gift);

        return GiftDto.fromEntity(gift);
    }
}
