package com.starkoracia.iotcontrollerservice.dto;

import com.starkoracia.iotcontrollerservice.model.DeviceStatus;
import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.starkoracia.iotcontrollerservice.model.Device}
 */
@Value
@Builder
public class DeviceDto implements Serializable {
    Long id;
    String name;
    Long userId;
    DeviceStatus status;
}