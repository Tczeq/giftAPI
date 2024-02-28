package pl.szlify.giftapi.strategy.creation;

import org.springframework.stereotype.Component;
import pl.szlify.giftapi.strategy.model.CreateKidCommand;
import pl.szlify.giftapi.strategy.model.Girl;

import java.time.LocalDate;
import java.util.Map;

@Component("GIRL")
public class GirlCreationStrategy implements KidCreationStrategy{
    @Override
    public Girl create(CreateKidCommand command) {
        Map<String, String> params = command.getParams();
        return Girl.builder()
                .firstName(params.get("firstName"))
                .lastName(params.get("lastName"))
                .birthday(LocalDate.parse(params.get("birthday")))
                .nagging(Boolean.parseBoolean(params.get("nagging")))
                .build();
    }
}
