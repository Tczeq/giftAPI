package pl.szlify.giftapi.strategy.creation;


import org.springframework.stereotype.Component;
import pl.szlify.giftapi.strategy.model.Boy;
import pl.szlify.giftapi.strategy.model.CreateKidCommand;

import java.time.LocalDate;
import java.util.Map;

@Component("BOY")
public class BoyCreationStrategy implements KidCreationStrategy {
    @Override
    public Boy create(CreateKidCommand command) {
        Map<String, String> params = command.getParams();
        return Boy.builder()
                .firstName(params.get("firstName"))
                .lastName(params.get("lastName"))
                .birthday(LocalDate.parse(params.get("birthday")))
                .pipeLength(Double.parseDouble(params.get("pipeLength")))
                .build();
    }
}
