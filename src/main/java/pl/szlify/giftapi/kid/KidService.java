package pl.szlify.giftapi.kid;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.szlify.giftapi.gift.GiftRepository;
import pl.szlify.giftapi.gift.model.Gift;
import pl.szlify.giftapi.kid.exception.InvalidGiftNumber;
import pl.szlify.giftapi.kid.exception.KidNotFoundException;
import pl.szlify.giftapi.kid.model.Kid;
import pl.szlify.giftapi.kid.model.command.CreateKidCommand;
import pl.szlify.giftapi.kid.model.command.UpdateKidCommand;
import pl.szlify.giftapi.kid.model.dto.KidDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KidService {
    private final GiftRepository giftRepository;
    private final KidRepository kidRepository;

    public List<KidDto> findall() {
        return kidRepository.findAll().stream()
                .map(KidDto::fromEntity)
                .toList();
    }

    public KidDto findById(int id) {
        return kidRepository.findById(id)
                .map(KidDto::fromEntity)
                .orElseThrow(() -> new KidNotFoundException(id));
    }

    public KidDto create(CreateKidCommand command) {
        Kid kid = command.toEntity();

        if (command.getGiftIds() != null && command.getGiftIds().size() > 3) {
            throw new InvalidGiftNumber();
        }

        if (command.getGiftIds() != null) {
            List<Gift> gifts = giftRepository.findAllById(command.getGiftIds());
            kid.setGifts(gifts);
        }

        return KidDto.fromEntity(kidRepository.save(kid));
    }

    @Transactional
    public void deleteById(int id) {
        Kid kid = kidRepository.findWithLockingById(id)
                .orElseThrow(() -> new KidNotFoundException(id));

        kid.setDeleted(false);
        kidRepository.save(kid);
    }

    @Transactional
    public KidDto update(int id, UpdateKidCommand updateKidCommand) {
        Kid kid = kidRepository.findWithLockingById(id)
                .orElseThrow(() -> new KidNotFoundException(id));
        if (kid.getGifts().size() > 3) {
            throw new InvalidGiftNumber();
        }

        List<Gift> allById = giftRepository.findAllById(updateKidCommand.getGiftId());

        kid.setGifts(allById);
        kidRepository.save(kid);

        return KidDto.fromEntity(kid);
    }
}
