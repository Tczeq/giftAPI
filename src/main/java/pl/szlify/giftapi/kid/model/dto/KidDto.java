package pl.szlify.giftapi.kid.model.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import pl.szlify.giftapi.gift.Gift;
import pl.szlify.giftapi.kid.model.Kid;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Builder
@EqualsAndHashCode
public class KidDto {

    private int id;

    private String firstName;

    private String lastName;

    private LocalDateTime birthday;

    private List<Gift> gifts;

    private boolean deleted;


    public static KidDto fromEntity(Kid kid) {
        return KidDto.builder()
                .id(kid.getId())
                .firstName(kid.getFirstName())
                .lastName(kid.getLastName())
                .birthday(kid.getBirthday())
                .gifts(kid.getGifts())
                .deleted(kid.isDeleted())
                .build();
    }
}
