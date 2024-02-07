package pl.szlify.giftapi.model.command;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class UpdateKidCommand {

    @Pattern(regexp = "[A-Z][a-z]{1,50}", message = "The firstName must begin with a capital letter and contain from 1 to 50 letters.")
    @NotNull(message = "name is obligatory")
    private String firstName;

    @Pattern(regexp = "[A-Z][a-z]{1,50}", message = "The lastName must begin with a capital letter and contain from 1 to 50 letters.")
    @NotNull(message = "name is obligatory")
    private String lastName;

    @NotNull(message = "day of birth is obligatory")
    private LocalDateTime birthday;

//    @Size(max = 3)
//    private List<Integer> giftsIds;
}
