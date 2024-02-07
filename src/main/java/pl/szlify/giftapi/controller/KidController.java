package pl.szlify.giftapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.szlify.giftapi.model.command.CreateGiftCommand;
import pl.szlify.giftapi.model.command.CreateKidCommand;
import pl.szlify.giftapi.model.command.UpdateGiftCommand;
import pl.szlify.giftapi.model.command.UpdateKidCommand;
import pl.szlify.giftapi.model.dto.GiftDto;
import pl.szlify.giftapi.model.dto.KidDto;
import pl.szlify.giftapi.service.GiftService;
import pl.szlify.giftapi.service.KidService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/kids")
public class KidController {
    private final KidService kidService;
    private final GiftService giftService;

    @GetMapping
    public List<KidDto> getAll() {
        return kidService.findall();
    }

    @GetMapping("/{id}")
    public KidDto getById(@PathVariable int id) {
        return kidService.findById(id);
    }

    @PostMapping
    public KidDto create(@Valid @RequestBody CreateKidCommand command) {
        return kidService.create(command);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        kidService.deleteById(id);
    }

    @PutMapping("/{id}")
    public KidDto updateKid(@PathVariable int id, @Valid @RequestBody UpdateKidCommand updateKidCommand) {
        return kidService.update(id, updateKidCommand);
    }

    @PutMapping("/{kidId}/gifts/{giftId}")
    public GiftDto updateGift(@PathVariable int kidId, @PathVariable int giftId, @Valid @RequestBody UpdateGiftCommand command) {
        return giftService.update(kidId, giftId, command);
    }

    @DeleteMapping("/{kidId}/gifts/{giftId}")
    public void deleteGiftFromKid(@PathVariable int giftId, @PathVariable int kidId) {
        giftService.deleteGiftFromKid(kidId, giftId);
    }

    @GetMapping("/{kidId}/gifts")
    public List<GiftDto> getGiftsByKidId(@PathVariable int kidId) {
        return giftService.findByKidId(kidId);
    }

    @GetMapping("/{kidId}/gifts/{giftId}")
    public GiftDto getOneGiftByIdAndByKidId(@PathVariable int giftId, @PathVariable int kidId) {
        return giftService.findByIdAndByKidId(giftId, kidId);
    }

    @PostMapping("/{kidId}/gifts")
    public GiftDto addGiftToKidId(@PathVariable int kidId, @Valid @RequestBody CreateGiftCommand command) {
        return giftService.addGiftToKidId(kidId, command);
    }
}
