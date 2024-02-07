package pl.szlify.giftapi.model.dto;


import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import pl.szlify.giftapi.model.Gift;

@Getter
@Setter
@Builder
@EqualsAndHashCode
public class GiftDto {

    private Integer id;

    private String name;

    private Double price;

    private boolean deleted;

    public static GiftDto fromEntity(Gift gift) {
        return GiftDto.builder()
                .id(gift.getId())
                .name(gift.getName())
                .price(gift.getPrice())
                .deleted(gift.isDeleted())
                .build();
    }
}
