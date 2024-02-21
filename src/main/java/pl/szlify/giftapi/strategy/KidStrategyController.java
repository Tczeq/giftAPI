package pl.szlify.giftapi.strategy;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.szlify.giftapi.model.dto.KidDto;
import pl.szlify.giftapi.strategy.model.CreateKidCommand;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/strategy/kids")
public class KidStrategyController {

    private final KidStrategyService kidStrategyService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public KidDto create(@RequestBody CreateKidCommand command) {
        return kidStrategyService.create(command);
    }
}
