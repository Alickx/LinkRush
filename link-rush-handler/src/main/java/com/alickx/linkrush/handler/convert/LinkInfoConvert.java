package com.alickx.linkrush.handler.convert;


import com.alickx.linkrush.api.dto.LinkInfoDTO;
import com.alickx.linkrush.handler.domain.LinkInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LinkInfoConvert {

    LinkInfoConvert INSTANCE = Mappers.getMapper(LinkInfoConvert.class);


    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "expireTime", source = "expirationDate")
    LinkInfo dtoToPo(LinkInfoDTO linkInfoDTO);

}
