package com.example.energy_20231120306.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.energy_20231120306.entity.Device;
import com.example.energy_20231120306.exception.BaseException;
import com.example.energy_20231120306.mapper.DeviceMapper;
import com.example.energy_20231120306.service.DeviceService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeviceServiceImpl extends ServiceImpl<DeviceMapper, Device> implements DeviceService {

    /**
     * 根据建筑物查询设备
     * @param buildingId 建筑物id
     * @return 设备列表
     */
    @Override
    public List<Device> listByBuilding(Long buildingId) {
        return lambdaQuery()
                .eq(Device::getBuildingId, buildingId)
                .list();
    }

    /**
     * 查询设备列表
     * @return 设备列表
     */
    @Override
    public List<Device> listAll() {
        return list();
    }

    /**
     * 新增设备
     * @param device 设备
     */
    @Override
    public void add(Device device) {
        // SN 唯一校验
        boolean exists = lambdaQuery()
                .eq(Device::getSn, device.getSn())
                .exists();

        if (exists) {
            throw new BaseException("设备 SN 已存在");
        }

        device.setId(null);
        device.setCreateTime(LocalDateTime.now());
        device.setStatus(1); // 默认 ONLINE
        save(device);
    }

    /**
     * 更新设备
     * @param device 设备
     */
    @Override
    public void update(Device device) {
        updateById(device);
    }

    /**
     * 修改设备状态
     * @param deviceId 设备id
     * @param status 状态
     */
    @Override
    public void changeStatus(Long deviceId, Integer status) {
        Device device = getById(deviceId);
        if (device == null) {
            throw new BaseException("设备不存在");
        }
        device.setStatus(status);
        updateById(device);
    }


}
