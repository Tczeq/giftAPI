package pl.szlify.giftapi.kid.model.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import pl.szlify.giftapi.gift.model.dto.GiftDto;
import pl.szlify.giftapi.kid.model.Kid;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Getter
@Builder
@EqualsAndHashCode
public class KidDto {

    private int id;

    private String firstName;

    private String lastName;

    private LocalDateTime birthday;

    private List<GiftDto> gifts;

    private boolean deleted;

    public static KidDto fromEntity(Kid kid) {
        List<GiftDto> gifts = Optional.ofNullable(kid.getGifts())
                .orElseGet(Collections::emptyList)
                .stream()
                .map(GiftDto::fromEntity)
                .toList();

        return KidDto.builder()
                .id(kid.getId())
                .firstName(kid.getFirstName())
                .lastName(kid.getLastName())
                .birthday(kid.getBirthday())
                .gifts(gifts)
                .deleted(kid.isDeleted())
                .build();
    }
}
