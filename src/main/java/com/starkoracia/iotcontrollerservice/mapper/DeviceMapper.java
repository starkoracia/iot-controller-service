package com.starkoracia.iotcontrollerservice.mapper;

import com.starkoracia.iotcontrollerservice.dto.DeviceDto;
import com.starkoracia.iotcontrollerservice.model.Device;
import com.starkoracia.iotcontrollerservice.model.User;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface DeviceMapper {
    @Mapping(source = "userId", target = "user")
    Device toEntity(DeviceDto deviceDto);

    @Mapping(source = "user.id", target = "userId")
    DeviceDto toDto(Device device);

    default User toUser(Long userId) {
        if (userId == null) {
            return null;
        }
        return User.builder().id(userId).build();
    }

    List<DeviceDto> toDtoList(List<Device> devices);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Device partialUpdate(DeviceDto deviceDto, @MappingTarget Device device);
}