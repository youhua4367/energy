package com.example.energy_20231120306.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.energy_20231120306.entity.AlarmRecord;
import com.example.energy_20231120306.mapper.AlarmRecordMapper;
import com.example.energy_20231120306.service.AlarmRecordService;
import org.springframework.stereotype.Service;

@Service
public class AlarmRecordServiceImpl extends ServiceImpl<AlarmRecordMapper, AlarmRecord> implements AlarmRecordService {
}
