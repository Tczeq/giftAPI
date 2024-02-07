package pl.szlify.giftapi.mapper;

import lombok.experimental.UtilityClass;
import org.springframework.stereotype.Service;
import pl.szlify.giftapi.model.Kid;
import pl.szlify.giftapi.model.command.CreateKidCommand;
import pl.szlify.giftapi.model.dto.KidDto;

@UtilityClass
public class KidMapper {

    public static KidDto mapToDto(Kid kid) {
        return KidDto.builder()
                .id(kid.getId())
                .firstName(kid.getFirstName())
                .lastName(kid.getLastName())
                .birthday(kid.getBirthday())
                .build();
    }


    public Kid mapFromCommand(CreateKidCommand command) {
        return Kid.builder()
                .firstName(command.getFirstName())
                .lastName(command.getLastName())
                .birthday(command.getBirthday())
                .build();
    }
}
