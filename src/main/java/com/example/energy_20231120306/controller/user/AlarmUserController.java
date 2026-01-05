package com.example.energy_20231120306.controller.user;

import com.example.energy_20231120306.entity.AlarmRecord;
import com.example.energy_20231120306.result.Result;
import com.example.energy_20231120306.service.AlarmRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user/alarm")
@Tag(name = "用户端告警接口")
@Slf4j
public class AlarmUserController {

    @Autowired
    private AlarmRecordService alarmRecordService;

    /**
     * 查询报警记录
     * @param deviceId 设备id
     * @return 报警记录
     */
    @GetMapping("/list")
    @Operation(summary = "查询设备告警")
    public Result<List<AlarmRecord>> list(Long deviceId) {
        log.info("查询设备告警：{}", deviceId);
        return Result.success(alarmRecordService.listByDevice(deviceId));
    }

    /**
     * 今日告警数量
     * @return 告警数量
     */
    @GetMapping("/count/today")
    @Operation(summary = "今日告警数量")
    public Result<Long> todayCount() {
        log.info("今日告警数量");
        return Result.success(alarmRecordService.countToday());
    }

    /**
     * 未处理的告警
     * @return 未处理告警数量
     */
    @GetMapping("/count/unhandled")
    @Operation(summary = "未处理告警数量")
    public Result<Long> unhandledCount() {
        log.info("未处理的告警数量");
        return Result.success(alarmRecordService.countUnHandled());
    }

    /**
     * 按类型统计告警数量
     * @return map类型
     */
    @GetMapping("/count/type")
    @Operation(summary = "按告警类型统计")
    public Result<Map<Integer, Long>> countByType() {
        log.info("按类型分类告警");
        return Result.success(alarmRecordService.countByType());
    }

    /**
     * 处理告警
     * @param alarmId 告警id
     * @return 处理成功
     */
    @PostMapping("/handle")
    @Operation(summary = "处理告警")
    public Result<String> handle(Long alarmId) {
        log.info("处理告警{}", alarmId);
        alarmRecordService.handle(alarmId);
        return Result.success("处理成功");
    }


}
