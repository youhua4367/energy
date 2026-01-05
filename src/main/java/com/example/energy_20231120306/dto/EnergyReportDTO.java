package com.example.energy_20231120306.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 设备能耗上报 DTO
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnergyReportDTO implements Serializable {
    private Long deviceId;
    // 电压
    private BigDecimal voltage;
    // 电流
    private BigDecimal current;
    // 功率
    private BigDecimal power;
    // 累计用电量
    private BigDecimal totalEnergy;
}
