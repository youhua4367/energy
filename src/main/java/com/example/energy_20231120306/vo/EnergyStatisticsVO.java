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
public class EnergyStatisticsVO {

    // 日期 / 月份
    private String time;

    // 用电量
    private BigDecimal energy;
}