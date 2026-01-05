package com.example.energy_20231120306.task;

import com.example.energy_20231120306.dto.EnergyReportDTO;
import com.example.energy_20231120306.entity.Device;
import com.example.energy_20231120306.service.DeviceService;
import com.example.energy_20231120306.service.EnergyDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Component
@Slf4j
class EnergySimulateTask {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private EnergyDataService energyDataService;

    private final Random random = new Random();

    /**
     * 每 5 秒模拟一次设备能耗上报
     */
    @Scheduled(fixedRate = 5000)
    public void simulateEnergyCollect() {
        // 只模拟启用状态的设备
        List<Device> devices = deviceService.lambdaQuery()
                .eq(Device::getStatus, 1)
                .last("limit 100") // 防止极端情况下设备过多
                .list();

        for (Device device : devices) {

            // 模拟夜晚和白天功率差异
            int hour = LocalDateTime.now().getHour();
            double factor = (hour >= 8 && hour <= 20) ? 1.0 + random.nextDouble() * 0.3  // 白天功率略高
                    : 0.5 + random.nextDouble() * 0.5;  // 夜晚功率低
            BigDecimal power = randomDecimal(100, 3000).multiply(BigDecimal.valueOf(factor));

            // 5 秒的能耗 = 功率 × 时间
            BigDecimal energyIncrement = power
                    .multiply(BigDecimal.valueOf(5))
                    .divide(BigDecimal.valueOf(3600), 6, RoundingMode.HALF_UP)
                    .divide(BigDecimal.valueOf(1000), 6, RoundingMode.HALF_UP);

            // 查询该设备最近一次累计电量
            BigDecimal lastTotalEnergy = energyDataService.getLastTotalEnergy(device.getId());

            BigDecimal totalEnergy = lastTotalEnergy.add(energyIncrement);

            EnergyReportDTO dto = EnergyReportDTO.builder()
                    .deviceId(device.getId())
                    .voltage(randomDecimal(210, 230))
                    .current(randomDecimal(1, 10))
                    .power(power)
                    .totalEnergy(totalEnergy)
                    .build();

            energyDataService.report(dto);
        }

        log.info("【能耗模拟任务】本次模拟上报设备数：{}", devices.size());
    }

    private BigDecimal randomDecimal(int min, int max) {
        return BigDecimal.valueOf(min + (max - min) * random.nextDouble()).setScale(2, RoundingMode.HALF_UP);
    }
}
