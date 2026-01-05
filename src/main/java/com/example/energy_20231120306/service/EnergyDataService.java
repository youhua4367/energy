package com.example.energy_20231120306.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.energy_20231120306.dto.EnergyReportDTO;
import com.example.energy_20231120306.entity.EnergyData;
import com.example.energy_20231120306.vo.EnergyOverviewVO;
import com.example.energy_20231120306.vo.EnergyRealtimeVO;
import com.example.energy_20231120306.vo.EnergyStatisticsVO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface EnergyDataService extends IService<EnergyData> {
    void report(EnergyReportDTO energyReportDTO);

    List<EnergyData> list(Long deviceId, LocalDateTime startTime, LocalDateTime endTime);

    EnergyOverviewVO overview();

    List<EnergyStatisticsVO> statistics(Long deviceId, String type);

    EnergyRealtimeVO realtime(Long deviceId);

    BigDecimal getLastTotalEnergy(Long id);
}
