package pl.szlify.giftapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.szlify.giftapi.mapper.KidMapper;
import pl.szlify.giftapi.mapper.IKidMapper;
import pl.szlify.giftapi.repository.GiftRepository;
import pl.szlify.giftapi.exception.TooManyGiftsException;
import pl.szlify.giftapi.repository.KidRepository;
import pl.szlify.giftapi.exception.InvalidAgeException;
import pl.szlify.giftapi.exception.KidNotFoundException;
import pl.szlify.giftapi.model.Kid;
import pl.szlify.giftapi.model.command.CreateKidCommand;
import pl.szlify.giftapi.model.command.UpdateKidCommand;
import pl.szlify.giftapi.model.dto.KidDto;

import java.time.LocalDate;
import java.time.Period;

import static pl.szlify.giftapi.mapper.IKidMapper.*;
import static pl.szlify.giftapi.mapper.KidMapper.mapToDto;

@Service
@RequiredArgsConstructor
public class KidService {
    private final GiftRepository giftRepository;
    private final KidRepository kidRepository;

    public Page<KidDto> findall(Pageable pageable) {
        return kidRepository.findAll(pageable)
                .map(KidMapper::mapToDto);
    }

//    public KidDto findById(int id) {
//        return kidRepository.findById(id)
//                .map(KidMapper::mapToDto)
//                .orElseThrow(() -> new KidNotFoundException(id));
//    }
    public KidDto findById(int id) {
        return kidRepository.findById(id)
                .map(INSTANCE::kidToDto)
                .orElseThrow(() -> new KidNotFoundException(id));
    }

    public boolean ageUnder18(LocalDate date) {
        Period between = Period.between(date, LocalDate.now());
        return between.getYears() < 18;
    }

    public KidDto create(CreateKidCommand command) {
        if(!ageUnder18(command.getBirthday())) {
            throw new InvalidAgeException(command.getBirthday());
        }

//        Kid kid = KidMapper.mapFromCommand(command);
        Kid kid = INSTANCE.mapFromCommand(command);
        kidRepository.save(kid);

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
