package pl.szlify.giftapi.kid;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.szlify.giftapi.gift.Gift;
import pl.szlify.giftapi.gift.GiftRepository;
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
        if(kid.getGifts().size() > 3) {
            throw new InvalidGiftNumber();
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
        if(kid.getGifts().size() > 3) {
            throw new InvalidGiftNumber();
        }

        List<Gift> allById = giftRepository.findAllById(updateKidCommand.getGiftId());

        kid.setGifts(allById);

        return KidDto.fromEntity(kidRepository.save(kid));
    }
}
