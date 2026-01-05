package com.example.energy_20231120306.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.energy_20231120306.entity.Device;

import java.util.List;

public interface DeviceService extends IService<Device> {
    List<Device> listByBuilding(Long buildingId);

    List<Device> listAll();

    void add(Device device);

    void update(Device device);

    void changeStatus(Long id, Integer status);
}
