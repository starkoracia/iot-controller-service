package com.starkoracia.iotcontrollerservice.repository;

import com.starkoracia.iotcontrollerservice.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceRepository extends JpaRepository<Device, Long> {
    List<Device> findByUserId(Long userId);
}
