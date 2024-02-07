package pl.szlify.giftapi.model.command;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;
import pl.szlify.giftapi.model.Gift;

@Data
@Builder
public class CreateGiftCommand {

    @Pattern(regexp = "[A-Z][a-z]{1,50}", message = "The name must begin with a capital letter and contain from 1 to 50 letters.")
    @NotNull(message = "name is obligatory")
    private String name;

    @Positive(message = "price cannot be nagative")
    private Double price;

    public Gift toEntity() {
        return Gift.builder()
                .name(name)
                .price(price)
                .build();
    }
}
