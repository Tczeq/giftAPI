package pl.szlify.giftapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.szlify.giftapi.repository.GiftRepository;
import pl.szlify.giftapi.exception.GiftNotFoundException;
import pl.szlify.giftapi.exception.TooManyGiftsException;
import pl.szlify.giftapi.model.Gift;
import pl.szlify.giftapi.model.command.CreateGiftCommand;
import pl.szlify.giftapi.model.command.UpdateGiftCommand;
import pl.szlify.giftapi.model.dto.GiftDto;
import pl.szlify.giftapi.repository.KidRepository;
import pl.szlify.giftapi.exception.KidNotFoundException;
import pl.szlify.giftapi.model.Kid;

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
    public GiftDto addGiftToKidId(int id, CreateGiftCommand command) {
        Kid kid = kidRepository.findWithLockingById(id)
                .orElseThrow(() -> new KidNotFoundException(id));

        if (kid.getGifts().size() >= 3) {
            throw new TooManyGiftsException();
        }

        Gift gift = command.toEntity();
        gift.setKid(kid);
        kid.getGifts().add(gift);

        return GiftDto.fromEntity(giftRepository.save(gift));
    }

    @Transactional
    public GiftDto update(int kidId, int giftId, UpdateGiftCommand command) {
        Gift gift = giftRepository.findWithLockingById(giftId)
                .orElseThrow(() -> new GiftNotFoundException(kidId));


        if (gift.getKid().getId() != kidId) {
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


    @Transactional
    public void deleteGiftFromKid(int kidId, int giftId) {
        Gift gift = giftRepository.findWithLockingById(giftId)
                .orElseThrow(() -> new GiftNotFoundException(kidId));

        gift.setDeleted(true);
        giftRepository.save(gift);
    }
}
