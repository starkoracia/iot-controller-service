package com.starkoracia.iotcontrollerservice.service;

import com.starkoracia.iotcontrollerservice.dto.DeviceDto;
import com.starkoracia.iotcontrollerservice.dto.DeviceRegistrationResponse;
import jakarta.transaction.Transactional;

import java.util.List;

public interface DeviceService {
    public DeviceRegistrationResponse registerDevice(DeviceDto deviceDto);

    @Transactional
    List<DeviceDto> getAllDevices(Long userId);
}
