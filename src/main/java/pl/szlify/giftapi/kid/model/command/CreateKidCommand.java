package pl.szlify.giftapi.kid.model.command;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.szlify.giftapi.common.Gift;
import pl.szlify.giftapi.kid.model.Kid;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@EqualsAndHashCode
public class CreateKidCommand {

    private String firstName;

    private String lastName;

    private LocalDateTime birthday;

    private List<Gift> gifts;

    public Kid toEntity() {
        return Kid.builder()
                .firstName(firstName)
                .lastName(lastName)
                .birthday(birthday)
                .build();
    }
}
