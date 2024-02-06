package pl.szlify.giftapi.gift;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.szlify.giftapi.gift.model.command.CreateGiftCommand;
import pl.szlify.giftapi.gift.model.dto.GiftDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/gifts")
public class GiftController {
    private final GiftService giftService;

    @GetMapping
    public List<GiftDto> getAll() {
        return giftService.findAll();
    }

    @GetMapping("/{id}")
    public List<GiftDto> getGiftsByKidId(@PathVariable int id) {
        return giftService.findByKidId(id);
    }

    @GetMapping("/{giftId}/kid/{kidId}")
    public GiftDto getOneGiftByIdAndByKidId(@PathVariable int giftId, @PathVariable int kidId) {
        return giftService.findByIdAndByKidId(giftId, kidId);
    }

    @PostMapping("/{id}")
    public GiftDto addGiftToKidId(@PathVariable int id, @RequestBody CreateGiftCommand command) {
        return giftService.addGiftToKidId(id, command);
    }


}
