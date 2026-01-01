package com.example.energy_20231120306.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.energy_20231120306.entity.EnergyData;
import com.example.energy_20231120306.mapper.EnergyDataMapper;
import com.example.energy_20231120306.service.EnergyDataService;
import org.springframework.stereotype.Service;

@Service
public class EnergyDataServiceImpl extends ServiceImpl<EnergyDataMapper, EnergyData> implements EnergyDataService {
}
