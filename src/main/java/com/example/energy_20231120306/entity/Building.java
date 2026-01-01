package com.example.energy_20231120306.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("building")
public class Building implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    // 位置编号
    private String locationCode;
    // 楼层数
    private Integer floorCount;
    // 建筑用途分类
    private String usageType;
    private LocalDateTime createTime;

}
