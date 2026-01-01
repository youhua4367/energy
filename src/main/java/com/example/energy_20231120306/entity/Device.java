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
@TableName("device")
public class Device implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String deviceName;
    // 设备序列号
    private String sn;
    // 设备状态，0表示未使用，1表示正在使用，2表示禁用
    private Integer status;
    private BigDecimal maxPower;
    private Long buildingId;
    private String roomNo;
    private LocalDateTime createTime;
}
