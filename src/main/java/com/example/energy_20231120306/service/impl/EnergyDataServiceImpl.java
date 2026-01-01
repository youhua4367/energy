package com.example.energy_20231120306.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.energy_20231120306.dto.EnergyReportDTO;
import com.example.energy_20231120306.entity.AlarmRecord;
import com.example.energy_20231120306.entity.Device;
import com.example.energy_20231120306.entity.EnergyData;
import com.example.energy_20231120306.exception.BaseException;
import com.example.energy_20231120306.mapper.AlarmRecordMapper;
import com.example.energy_20231120306.mapper.DeviceMapper;
import com.example.energy_20231120306.mapper.EnergyDataMapper;
import com.example.energy_20231120306.service.EnergyDataService;
import com.example.energy_20231120306.vo.EnergyOverviewVO;
import com.example.energy_20231120306.vo.EnergyRealtimeVO;
import com.example.energy_20231120306.vo.EnergyStatisticsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EnergyDataServiceImpl extends ServiceImpl<EnergyDataMapper, EnergyData> implements EnergyDataService {

    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private AlarmRecordMapper alarmRecordMapper;

    /**
     * 填报数据
     * @param energyReportDTO 能源实体
     */
    @Override
    public void report(EnergyReportDTO energyReportDTO) {
        // 校验设备是否存在
        Device device = deviceMapper.selectById(energyReportDTO.getDeviceId());
        if (device == null) {
            throw new BaseException("设备不存在");
        }

        // 校验设备状态（1 = 启用）
        if (device.getStatus() != 1) {
            throw new BaseException("设备未启用");
        }

        // 保存能耗数据
        EnergyData energyData = EnergyData.builder()
                .deviceId(energyReportDTO.getDeviceId())
                .voltage(energyReportDTO.getVoltage())
                .current(energyReportDTO.getCurrent())
                .power(energyReportDTO.getPower())
                .totalEnergy(energyReportDTO.getTotalEnergy())
                .collectTime(LocalDateTime.now())
                .build();

        this.save(energyData);

        // 功率超限告警
        if (energyReportDTO.getPower() != null
                && device.getMaxPower() != null
                && energyReportDTO.getPower().compareTo(device.getMaxPower()) > 0) {

            AlarmRecord alarm = AlarmRecord.builder()
                    .deviceId(device.getId())
                    .alarmType(1) // 1 = 功率超限
                    .alarmValue(energyReportDTO.getPower())
                    .alarmDesc("功率超过设备最大阈值")
                    .triggerTime(LocalDateTime.now())
                    .build();

            alarmRecordMapper.insert(alarm);
        }
    }

    /**
     * 查询能源数据列表
     * @param deviceId 设备id
     * @param startTime 起始时间
     * @param endTime 终止时间
     * @return 能源列表
     */
    @Override
    public List<EnergyData> list(Long deviceId, LocalDateTime startTime, LocalDateTime endTime) {
        return lambdaQuery()
                .eq(deviceId != null, EnergyData::getDeviceId, deviceId)
                .ge(startTime != null, EnergyData::getCollectTime, startTime)
                .le(endTime != null, EnergyData::getCollectTime, endTime)
                .orderByDesc(EnergyData::getCollectTime)
                .list();
    }

    /**
     * 总览能源
     * @return 能源实体
     */
    @Override
    public EnergyOverviewVO overview() {
        // 今日时间范围
        LocalDateTime todayStart = LocalDate.now().atStartOfDay();
        LocalDateTime todayEnd = todayStart.plusDays(1);

        // 本月时间范围
        LocalDateTime monthStart = LocalDate.now()
                .withDayOfMonth(1)
                .atStartOfDay();
        LocalDateTime monthEnd = monthStart.plusMonths(1);

        // 今日用电量
        BigDecimal todayEnergy = baseMapper.selectSumEnergy(todayStart, todayEnd);
        if (todayEnergy == null) {
            todayEnergy = BigDecimal.ZERO;
        }

        // 本月用电量
        BigDecimal monthEnergy = baseMapper.selectSumEnergy(monthStart, monthEnd);
        if (monthEnergy == null) {
            monthEnergy = BigDecimal.ZERO;
        }

        // 设备总数
        Long deviceCount = deviceMapper.selectCount(null);

        // 今日告警数
        Long alarmCount = alarmRecordMapper.selectCount(
                new QueryWrapper<AlarmRecord>()
                        .ge("trigger_time", todayStart)
                        .lt("trigger_time", todayEnd)
        );


        return EnergyOverviewVO.builder()
                .todayEnergy(todayEnergy)
                .monthEnergy(monthEnergy)
                .deviceCount(deviceCount)
                .todayAlarmCount(alarmCount)
                .build();
    }

    /**
     * 能源统计
     * @param deviceId 设备id
     * @param type 统计方式
     * @return 能源列表
     */
    @Override
    public List<EnergyStatisticsVO> statistics(Long deviceId, String type) {
        if (deviceId == null) {
            throw new BaseException("设备ID不能为空");
        }

        if ("day".equals(type)) {
            return baseMapper.statisticsByDay(deviceId);
        }

        if ("month".equals(type)) {
            return baseMapper.statisticsByMonth(deviceId);
        }

        throw new BaseException("统计类型错误");
    }

    /**
     * 实时数据
     * @param deviceId 设备id
     * @return 实时数据
     */
    @Override
    public EnergyRealtimeVO realtime(Long deviceId) {
        if (deviceId == null) {
            throw new BaseException("设备ID不能为空");
        }

        EnergyData data = baseMapper.selectLatest(deviceId);
        if (data == null) {
            throw new BaseException("暂无实时数据");
        }

        return EnergyRealtimeVO.builder()
                .deviceId(data.getDeviceId())
                .voltage(data.getVoltage())
                .current(data.getCurrent())
                .power(data.getPower())
                .totalEnergy(data.getTotalEnergy())
                .collectTime(data.getCollectTime())
                .build();
    }
}
