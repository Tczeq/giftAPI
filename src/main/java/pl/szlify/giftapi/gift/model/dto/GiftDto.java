package pl.szlify.giftapi.gift.model.dto;


import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import pl.szlify.giftapi.gift.model.Gift;

@Getter
@Setter
@Builder
@EqualsAndHashCode
public class GiftDto {

    private Integer id;

    private String name;

    private Double price;

    private Integer kidId;

    private boolean deleted;

    public static GiftDto fromEntity(Gift gift) {
        return GiftDto.builder()
                .id(gift.getId())
                .name(gift.getName())
                .price(gift.getPrice())
                .kidId(gift.getKid() != null ? gift.getKid().getId() : null)
                .deleted(gift.isDeleted())
                .build();
    }
}
