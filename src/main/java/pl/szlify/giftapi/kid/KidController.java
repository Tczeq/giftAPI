package pl.szlify.giftapi.kid;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.szlify.giftapi.kid.model.command.CreateKidCommand;
import pl.szlify.giftapi.kid.model.dto.KidDto;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class KidController {
    private final KidService kidService;


    @GetMapping
    public List<KidDto> getAll(){
        return kidService.findall();
    }

    @GetMapping("/{id}")
    public KidDto getById(@PathVariable int id) {
        return kidService.findById(id);
    }

    @PostMapping
    public KidDto create(@Valid @RequestBody CreateKidCommand command){
        return kidService.create(command);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        kidService.deleteById(id);
    }
}
