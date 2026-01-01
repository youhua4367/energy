package com.example.energy_20231120306.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnergyRealtimeVO {
    private Long deviceId;

    // 电压
    private BigDecimal voltage;

    // 电流
    private BigDecimal current;

    // 功率
    private BigDecimal power;

    // 累积用电量
    private BigDecimal totalEnergy;

    // 数据时间
    private LocalDateTime collectTime;
}
