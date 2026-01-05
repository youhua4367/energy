package com.example.energy_20231120306.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.energy_20231120306.entity.AlarmRecord;

import java.util.List;
import java.util.Map;

public interface AlarmRecordService extends IService<AlarmRecord> {
    List<AlarmRecord> listByDevice(Long deviceId);

    Long countToday();

    Long countUnHandled();

    Map<Integer, Long> countByType();

    void handle(Long alarmId);
}
