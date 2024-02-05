package pl.szlify.giftapi.gift;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
public enum Gift {



    KITKAT("Kitkat", 20.02),
    MARS("Mars",20.02);

    private final String name;
    private final Double price;
}
