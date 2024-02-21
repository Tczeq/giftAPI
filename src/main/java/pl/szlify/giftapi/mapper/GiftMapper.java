package pl.szlify.giftapi.mapper;

import lombok.experimental.UtilityClass;
import pl.szlify.giftapi.model.Gift;
import pl.szlify.giftapi.model.Kid;
import pl.szlify.giftapi.model.command.CreateGiftCommand;
import pl.szlify.giftapi.model.command.CreateKidCommand;
import pl.szlify.giftapi.model.dto.GiftDto;

@UtilityClass
public class GiftMapper {

    public static GiftDto mapToDto(Gift gift) {
        return GiftDto.builder()
                .id(gift.getId())
                .name(gift.getName())
                .price(gift.getPrice())
//                .deleted(gift.isDeleted())
                .build();
    }

    public Gift mapFromCommand(CreateGiftCommand command) {
        return Gift.builder()
                .name(command.getName())
                .price(command.getPrice())
                .build();
    }
}
