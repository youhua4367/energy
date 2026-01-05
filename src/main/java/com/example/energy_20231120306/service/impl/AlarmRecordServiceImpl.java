package com.example.energy_20231120306.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.energy_20231120306.entity.AlarmRecord;
import com.example.energy_20231120306.exception.BaseException;
import com.example.energy_20231120306.mapper.AlarmRecordMapper;
import com.example.energy_20231120306.service.AlarmRecordService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AlarmRecordServiceImpl extends ServiceImpl<AlarmRecordMapper, AlarmRecord> implements AlarmRecordService {

    /**
     * 查询报警记录
     * @param deviceId 设备id
     * @return 报警记录
     */
    @Override
    public List<AlarmRecord> listByDevice(Long deviceId) {
        return lambdaQuery()
                .eq(AlarmRecord::getDeviceId, deviceId)
                .orderByDesc(AlarmRecord::getTriggerTime)
                .list();
    }

    /**
     * 今日告警数量
     * @return 告警数量
     */
    @Override
    public Long countToday() {
        return lambdaQuery()
                .ge(AlarmRecord::getTriggerTime,
                        LocalDate.now().atStartOfDay())
                .count();
    }

    /**
     * 未处理的告警
     * @return 未处理告警数量
     */
    @Override
    public Long countUnHandled() {
        return lambdaQuery()
                .eq(AlarmRecord::getStatus, 0)
                .count();
    }

    /**
     * 按类型统计告警数量
     * @return map类型
     */
    @Override
    public Map<Integer, Long> countByType() {
        return lambdaQuery()
                .list()
                .stream()
                .collect(Collectors.groupingBy(
                        AlarmRecord::getAlarmType,
                        Collectors.counting()
                ));
    }

    /**
     * 处理告警
     * @param alarmId 告警id
     */
    @Override
    public void handle(Long alarmId) {
        AlarmRecord record = getById(alarmId);
        if (record == null) {
            throw new BaseException("告警不存在");
        }
        if (record.getStatus() == 1) {
            throw new BaseException("告警已处理");
        }

        record.setStatus(1);
        record.setHandleTime(LocalDateTime.now());
        updateById(record);
    }

}
