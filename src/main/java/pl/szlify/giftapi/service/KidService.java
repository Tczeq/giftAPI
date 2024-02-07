package pl.szlify.giftapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.szlify.giftapi.mapper.KidMapper;
import pl.szlify.giftapi.repository.GiftRepository;
import pl.szlify.giftapi.exception.TooManyGiftsException;
import pl.szlify.giftapi.model.Gift;
import pl.szlify.giftapi.model.dto.GiftDto;
import pl.szlify.giftapi.repository.KidRepository;
import pl.szlify.giftapi.exception.InvalidAgeException;
import pl.szlify.giftapi.exception.KidNotFoundException;
import pl.szlify.giftapi.model.Kid;
import pl.szlify.giftapi.model.command.CreateKidCommand;
import pl.szlify.giftapi.model.command.UpdateKidCommand;
import pl.szlify.giftapi.model.dto.KidDto;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import static pl.szlify.giftapi.mapper.KidMapper.mapToDto;

@Service
@RequiredArgsConstructor
public class KidService {
    private final GiftRepository giftRepository;
    private final KidRepository kidRepository;

    public List<KidDto> findall() {
        return kidRepository.findAll().stream()
                .map(KidMapper::mapToDto)
                .toList();
    }

    public KidDto findById(int id) {
        return kidRepository.findById(id)
                .map(KidMapper::mapToDto)
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
//        if (command.getGifts().size() >= 3) {
//            throw new TooManyGiftsException();
//        }

        Kid kid = KidMapper.mapFromCommand(command);
        kidRepository.save(kid);

//        List<Gift> gifts = new ArrayList<>();
//        if (command.getGifts() != null) {
//            for (GiftDto element : command.getGifts()) {
//                Gift gift = new Gift();
//                gift.setName(element.getName());
//                gift.setPrice(element.getPrice());
//                gift.setDeleted(element.isDeleted());
//                gift.setKid(kid);
//                gifts.add(gift);
//            }
//
//            if (!gifts.isEmpty()) {
//                giftRepository.saveAll(gifts);
//                kid.setGifts(gifts);
//            }
//        }
        return mapToDto(kidRepository.save(kid));
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

//        List<Gift> allById = giftRepository.findAllById(updateKidCommand.getGiftsIds());
//        kid.setGifts(allById);
//        kidRepository.save(kid);
        return mapToDto(kid);
    }


}
