package com.starkoracia.iotcontrollerservice.service;

import com.starkoracia.iotcontrollerservice.dto.DeviceDto;
import com.starkoracia.iotcontrollerservice.dto.DeviceRegistrationResponse;
import com.starkoracia.iotcontrollerservice.exception.DataValidationException;
import com.starkoracia.iotcontrollerservice.model.Device;
import com.starkoracia.iotcontrollerservice.mapper.DeviceMapper;
import com.starkoracia.iotcontrollerservice.model.DeviceStatus;
import com.starkoracia.iotcontrollerservice.repository.DeviceRepository;
import com.starkoracia.iotcontrollerservice.repository.UserRepository;
import com.starkoracia.iotcontrollerservice.util.MqttUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService {
    private final DeviceRepository deviceRepository;
    private final UserRepository userRepository;
    private final DeviceMapper deviceMapper;

    @Override
    @Transactional
    public DeviceRegistrationResponse registerDevice(DeviceDto deviceDto) {
        if (!userRepository.existsById(deviceDto.getUserId())) {
            throw new DataValidationException(String.format("User with id: '%s' not exists", deviceDto.getUserId()));
        }
        Device newDevice = deviceMapper.toEntity(deviceDto);
        newDevice.setStatus(DeviceStatus.NEW);
        deviceRepository.save(newDevice);
        return buildResponse(newDevice);
    }

    @Override
    @Transactional
    public List<DeviceDto> getAllDevices(Long userId) {
        List<Device> allDevices = deviceRepository.findByUserId(userId);
        return deviceMapper.toDtoList(allDevices);
    }

    private DeviceRegistrationResponse buildResponse(Device device) {
        Long userId = device.getUser().getId();
        Long deviceId = device.getId();
        String deviceName = device.getName();
        String commandTopic = MqttUtils.getCommandTopic(userId, deviceId);
        String telemetryTopic = MqttUtils.getTelemetryTopic(userId, deviceId);

        return DeviceRegistrationResponse.builder()
                .deviceId(deviceId)
                .deviceName(deviceName)
                .commandTopic(commandTopic)
                .telemetryTopic(telemetryTopic)
                .build();
    }
}
