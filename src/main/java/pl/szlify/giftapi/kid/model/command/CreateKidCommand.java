package pl.szlify.giftapi.kid.model.command;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.szlify.giftapi.gift.model.Gift;
import pl.szlify.giftapi.gift.model.dto.GiftDto;
import pl.szlify.giftapi.kid.model.Kid;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@EqualsAndHashCode
public class CreateKidCommand {

    @Pattern(regexp = "[A-Z][a-z]{1,50}", message = "The firstName must begin with a capital letter and contain from 1 to 50 letters.")
    @NotNull(message = "name is obligatory")
    private String firstName;

    @Pattern(regexp = "[A-Z][a-z]{1,50}", message = "The lastName must begin with a capital letter and contain from 1 to 50 letters.")
    @NotNull(message = "name is obligatory")
    private String lastName;

    @NotNull(message = "day of birth is obligatory")
    @PastOrPresent
    private LocalDateTime birthday;

    @Size(max = 3)
    private List<GiftDto> gifts;

    public Kid toEntity() {
        return Kid.builder()
                .firstName(firstName)
                .lastName(lastName)
                .birthday(birthday)
                .build();
    }
}
