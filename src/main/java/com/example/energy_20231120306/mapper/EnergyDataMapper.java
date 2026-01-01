package com.example.energy_20231120306.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.energy_20231120306.entity.EnergyData;
import com.example.energy_20231120306.vo.EnergyStatisticsVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface EnergyDataMapper extends BaseMapper<EnergyData> {


    @Select("""
        select sum(total_energy)
        from energy_data
        where collect_time >= #{start}
          and collect_time < #{end}
    """)
    BigDecimal selectSumEnergy(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @Select("""
    select 
      date_format(collect_time, '%Y-%m-%d') as time,
      sum(total_energy) as energy
    from energy_data
    where device_id = #{deviceId}
    group by date_format(collect_time, '%Y-%m-%d')
    order by time
    """)
    List<EnergyStatisticsVO> statisticsByDay(@Param("deviceId") Long deviceId);


    @Select("""
    select 
      date_format(collect_time, '%Y-%m') as time,
      sum(total_energy) as energy
    from energy_data
    where device_id = #{deviceId}
    group by date_format(collect_time, '%Y-%m')
    order by time
    """)
    List<EnergyStatisticsVO> statisticsByMonth(@Param("deviceId") Long deviceId);

    @Select("""
    select *
    from energy_data
    where device_id = #{deviceId}
    order by collect_time desc
    limit 1
    """)
    EnergyData selectLatest(Long deviceId);
}
