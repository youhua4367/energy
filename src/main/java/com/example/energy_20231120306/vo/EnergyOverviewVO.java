package com.example.energy_20231120306.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnergyOverviewVO {

    // 今日用电量（kWh）
    private BigDecimal todayEnergy;

    // 本月用电量（kWh）
    private BigDecimal monthEnergy;

    // 设备总数
    private Long deviceCount;

    // 今日告警数
    private Long todayAlarmCount;
}