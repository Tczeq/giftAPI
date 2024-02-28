package pl.szlify.giftapi.strategy;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.szlify.giftapi.mapper.IKidMapper;
import pl.szlify.giftapi.model.Kid;
import pl.szlify.giftapi.model.dto.KidDto;
import pl.szlify.giftapi.repository.KidRepository;
import pl.szlify.giftapi.strategy.creation.KidCreationStrategy;
import pl.szlify.giftapi.strategy.model.CreateKidCommand;

import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class KidStrategyService {

    private final Map<String, KidCreationStrategy> creationStrategies;
//    @Autowired
    private final KidRepository kidRepository;

    public KidDto create(CreateKidCommand command) {
        KidCreationStrategy creationStrategy = creationStrategies.get(command.getType());
        Kid kid = creationStrategy.create(command);
        log.info("created: {}", kid);

//        kidRepository.save(kid);

        return IKidMapper.INSTANCE.kidToDto(kid);
    }
}
