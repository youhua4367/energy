package com.example.energy_20231120306.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("energy_data")
public class EnergyData implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long deviceId;
    // 电压 V
    private BigDecimal voltage;
    // 电流 A
    private BigDecimal current;
    // 功率 W
    private BigDecimal power;
    // 累积用电量 kWh
    private BigDecimal totalEnergy;
    private LocalDateTime collectTime;
}
