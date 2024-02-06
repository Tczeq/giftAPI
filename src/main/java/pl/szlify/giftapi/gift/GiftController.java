package pl.szlify.giftapi.gift;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.szlify.giftapi.gift.model.command.CreateGiftCommand;
import pl.szlify.giftapi.gift.model.command.UpdateGiftCommand;
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

    @GetMapping("/{giftId}/{kidId}")
    public GiftDto getOneGiftByIdAndByKidId(@PathVariable int giftId, @PathVariable int kidId) {
        return giftService.findByIdAndByKidId(giftId, kidId);
    }

    @PostMapping
    public GiftDto addGiftToKidId(@RequestBody CreateGiftCommand command) {
        return giftService.addGiftToKidId(command);
    }

//    @PutMapping("/{id}/list")
//    public GiftDto update(@PathVariable int id, @Valid @RequestBody UpdateGiftCommand command) {
//        return giftService.update(id, command);
//    }
    @PutMapping("/kids/{kidId}/gifts/{giftId}")
    public GiftDto updateGift(@PathVariable int kidId, @PathVariable int giftId, @Valid @RequestBody UpdateGiftCommand command) {
        return giftService.update(kidId, giftId, command);
    }

}
