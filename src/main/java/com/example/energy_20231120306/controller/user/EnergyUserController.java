package com.example.energy_20231120306.controller.user;

import com.example.energy_20231120306.dto.EnergyReportDTO;
import com.example.energy_20231120306.entity.EnergyData;
import com.example.energy_20231120306.result.Result;
import com.example.energy_20231120306.service.EnergyDataService;
import com.example.energy_20231120306.vo.EnergyOverviewVO;
import com.example.energy_20231120306.vo.EnergyRealtimeVO;
import com.example.energy_20231120306.vo.EnergyStatisticsVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@Tag(name = "用户端能源相关接口", description = "用户端能源相关接口")
@Slf4j
@RequestMapping("/user/energy")
public class EnergyUserController {

    @Autowired
    private EnergyDataService energyDataService;

    /**
     * 设备上传能源数据
     * @param energyReportDTO 数据接收实体
     * @return 成功标志
     */
    @PostMapping("/report")
    @Operation(summary = "上传能源数据")
    public Result<String> report(@RequestBody EnergyReportDTO energyReportDTO) {
        log.info("设备上传能源数据：{}", energyReportDTO);
        energyDataService.report(energyReportDTO);
        return Result.success("上报成功");
    }

    /**
     * 查询设备能源数据列表
     * @param deviceId 设备id
     * @param startTime 起始时间
     * @param endTime 终止时间
     * @return 能源数据列表
     */
    @GetMapping("/list")
    @Operation(summary = "查询设备能耗数据列表")
    public Result<List<EnergyData>> list(
            Long deviceId,
            LocalDateTime startTime,
            LocalDateTime endTime) {
        log.info("查询能源数据列表:{},{},{}", deviceId, startTime, endTime);

        return Result.success(energyDataService.list(deviceId, startTime, endTime));
    }

    /**
     * 能源数据总览
     * @return 能源数据
     */
    @GetMapping("/overview")
    @Operation(summary = "能源数据概览")
    public Result<EnergyOverviewVO> overview() {
        log.info("查询能源概览数据");
        return Result.success(energyDataService.overview());
    }

    /**
     * 能源统计
     * @param deviceId 设备id
     * @param type 统计的类型
     * @return 能源列表
     */
    @GetMapping("/statistics")
    @Operation(summary = "能耗统计（折线图）")
    public Result<List<EnergyStatisticsVO>> statistics(
            Long deviceId,
            String type) {

        log.info("查询能耗统计：deviceId={}, type={}", deviceId, type);
        return Result.success(energyDataService.statistics(deviceId, type));
    }

    /**
     * 实时数据
     * @param deviceId 设备id
     * @return 实时的设备数据
     */
    @GetMapping("/realtime")
    @Operation(summary = "实时监控数据")
    public Result<EnergyRealtimeVO> realtime(Long deviceId) {
        log.info("查询实时数据：deviceId={}", deviceId);
        return Result.success(energyDataService.realtime(deviceId));
    }


}
