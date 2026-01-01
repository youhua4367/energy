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
@TableName("alarm_record")
public class AlarmRecord implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long deviceId;
    // 告警类型
    private Integer alarmType;
    // 告警数值
    private BigDecimal alarmValue;
    // 告警描述
    private String alarmDesc;
    // 告警时间
    private LocalDateTime triggerTime;
}
