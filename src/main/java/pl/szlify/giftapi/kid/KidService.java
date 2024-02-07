package pl.szlify.giftapi.kid;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.szlify.giftapi.gift.GiftRepository;
import pl.szlify.giftapi.gift.exception.TooManyGiftsException;
import pl.szlify.giftapi.gift.model.Gift;
import pl.szlify.giftapi.gift.model.dto.GiftDto;
import pl.szlify.giftapi.kid.exception.InvalidAgeException;
import pl.szlify.giftapi.kid.exception.KidNotFoundException;
import pl.szlify.giftapi.kid.model.Kid;
import pl.szlify.giftapi.kid.model.command.CreateKidCommand;
import pl.szlify.giftapi.kid.model.command.UpdateKidCommand;
import pl.szlify.giftapi.kid.model.dto.KidDto;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
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

    public boolean ageUnder18(LocalDateTime dateTime) {
        Period between = Period.between(dateTime.toLocalDate(), LocalDateTime.now().toLocalDate());
        return between.getYears() < 18;
    }

    public KidDto create(CreateKidCommand command) {
        if(!ageUnder18(command.getBirthday())) {
            throw new InvalidAgeException(command.getBirthday());
        }
        if (command.getGifts().size() >= 3) {
            throw new TooManyGiftsException();
        }

        Kid kid = command.toEntity();
        kidRepository.save(kid);

        List<Gift> gifts = new ArrayList<>();
        if (command.getGifts() != null) {
            for (GiftDto element : command.getGifts()) {
                Gift gift = new Gift();
                gift.setName(element.getName());
                gift.setPrice(element.getPrice());
                gift.setDeleted(element.isDeleted());
                gift.setKid(kid);
                gifts.add(gift);
            }

            if (!gifts.isEmpty()) {
                giftRepository.saveAll(gifts);
                kid.setGifts(gifts);
            }
        }        return KidDto.fromEntity(kidRepository.save(kid));
    }


    // SOFTDELETE
    @Transactional
    public void deleteById(int id) {
        Kid kid = kidRepository.findWithLockingById(id)
                .orElseThrow(() -> new KidNotFoundException(id));
        kid.setDeleted(true);
        kidRepository.save(kid);
    }

    // NORMAL DELETE
//    @Transactional
//    public void deleteById(int id) {
//        kidRepository.deleteById(id);
//    }

    @Transactional
    public KidDto update(int id, UpdateKidCommand updateKidCommand) {
        Kid kid = kidRepository.findWithLockingById(id)
                .orElseThrow(() -> new KidNotFoundException(id));
        if (kid.getGifts().size() > 3) {
            throw new TooManyGiftsException();
        }

        List<Gift> allById = giftRepository.findAllById(updateKidCommand.getGiftsIds());
        kid.setGifts(allById);
        kidRepository.save(kid);
        return KidDto.fromEntity(kid);
    }


}
