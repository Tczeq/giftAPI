package pl.szlify.giftapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.szlify.giftapi.model.Kid;
import pl.szlify.giftapi.model.command.CreateKidCommand;
import pl.szlify.giftapi.model.dto.KidDto;

@Mapper
public interface IKidMapper {
    IKidMapper INSTANCE = Mappers.getMapper(IKidMapper.class);

    KidDto kidToDto(Kid kid);

    Kid mapFromCommand(CreateKidCommand command);
}
