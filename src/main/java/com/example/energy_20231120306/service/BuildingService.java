package com.example.energy_20231120306.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.energy_20231120306.entity.Building;

import java.util.List;

public interface BuildingService extends IService<Building> {
    List<Building> listAll();
}
