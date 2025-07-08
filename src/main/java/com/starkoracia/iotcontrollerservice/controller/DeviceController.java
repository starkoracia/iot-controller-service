package com.starkoracia.iotcontrollerservice.controller;

import com.starkoracia.iotcontrollerservice.dto.DeviceDto;
import com.starkoracia.iotcontrollerservice.dto.DeviceRegistrationResponse;
import com.starkoracia.iotcontrollerservice.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/device")
@RequiredArgsConstructor
public class DeviceController {
    private final DeviceService deviceService;

    @PostMapping("/register")
    public ResponseEntity<DeviceRegistrationResponse> registerDevice(@RequestBody DeviceDto deviceDto) {
        DeviceRegistrationResponse response = deviceService.registerDevice(deviceDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<DeviceDto>> getAllDevices(@RequestParam Long userId) {
        List<DeviceDto> devicesByUser = deviceService.getAllDevices(userId);
        return ResponseEntity.ok(devicesByUser);
    }
}
