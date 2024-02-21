package pl.szlify.giftapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.szlify.giftapi.model.Kid;
import pl.szlify.giftapi.model.dto.KidDto;

@Mapper
public interface KidMapperMapStruct {

//    @Mapping(target = "kidId", source = "id")
    KidDto kidToDto(Kid kid);
}
