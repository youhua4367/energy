package com.example.energy_20231120306.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.energy_20231120306.entity.Building;
import com.example.energy_20231120306.mapper.BuildingMapper;
import com.example.energy_20231120306.service.BuildingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuildingServiceImpl extends ServiceImpl<BuildingMapper, Building> implements BuildingService {

    /**
     * 查询所有建筑
     * @return 建筑列表
     */
    @Override
    public List<Building> listAll() {
        return this.list();
    }
}
