package pl.szlify.giftapi.kid.model.command;

import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UpdateKidCommand {

    @Size(max = 3)
    private List<Integer> giftsId;
}
