package pl.szlify.giftapi.gift.model.command;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateGiftCommand {

    @Pattern(regexp = "[A-Z][a-z]{1,50}", message = "The name must begin with a capital letter and contain from 1 to 50 letters.")
    @NotNull(message = "name is obligatory")
    private String name;

    @Positive(message = "price cannot be nagative")
    private Double price;
}
