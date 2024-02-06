package pl.szlify.giftapi.kid;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.szlify.giftapi.gift.GiftService;
import pl.szlify.giftapi.gift.model.command.UpdateGiftCommand;
import pl.szlify.giftapi.gift.model.dto.GiftDto;
import pl.szlify.giftapi.kid.model.command.CreateKidCommand;
import pl.szlify.giftapi.kid.model.command.UpdateKidCommand;
import pl.szlify.giftapi.kid.model.dto.KidDto;

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

    @PutMapping("/{kidId}/gift/{giftId}")
    public GiftDto updateGift(@PathVariable int kidId, @PathVariable int giftId, @Valid @RequestBody UpdateGiftCommand command) {
        return giftService.update(kidId, giftId, command);
    }

    @DeleteMapping("/{kidId}/gift/{giftId}")
    public void deleteGiftFromKid(@PathVariable int giftId, @PathVariable int kidId) {
        giftService.deleteGiftFromKid(kidId, giftId);
    }
}
