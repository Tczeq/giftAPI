package pl.szlify.giftapi.strategy.creation;

import pl.szlify.giftapi.model.Kid;
import pl.szlify.giftapi.strategy.model.CreateKidCommand;

public interface KidCreationStrategy {
    Kid create(CreateKidCommand command);
}
