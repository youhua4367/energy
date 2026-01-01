package com.example.energy_20231120306.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.energy_20231120306.entity.Device;
import com.example.energy_20231120306.mapper.DeviceMapper;
import com.example.energy_20231120306.service.DeviceService;
import org.springframework.stereotype.Service;

@Service
public class DeviceServiceImpl extends ServiceImpl<DeviceMapper, Device> implements DeviceService {
}
