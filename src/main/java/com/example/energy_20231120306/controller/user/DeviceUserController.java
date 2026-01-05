package com.example.energy_20231120306.controller.user;

import com.example.energy_20231120306.entity.Device;
import com.example.energy_20231120306.result.Result;
import com.example.energy_20231120306.service.DeviceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/device")
@Tag(name = "用户端设备接口")
@Slf4j
public class DeviceUserController {

    @Autowired
    private DeviceService deviceService;

    /**
     * 根据建筑物查询设备
     * @param buildingId 建筑物id
     * @return 设备列表
     */
    @GetMapping("/list")
    @Operation(summary = "按建筑查询设备")
    public Result<List<Device>> list(Long buildingId) {
        log.info("按建筑查询设备:{}", buildingId);
        return Result.success(deviceService.listByBuilding(buildingId));
    }
}
