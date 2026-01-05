package com.example.energy_20231120306.controller.admin;

import com.example.energy_20231120306.entity.Device;
import com.example.energy_20231120306.result.Result;
import com.example.energy_20231120306.service.DeviceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/device")
@Tag(name = "管理员端设备管理接口")
@Slf4j
public class DeviceAdminController {

    @Autowired
    private DeviceService deviceService;

    /**
     * 查询设备列表
     * @return 设备列表
     */
    @GetMapping("/list")
    @Operation(summary = "查询设备列表")
    public Result<List<Device>> list() {
        log.info("查询设备列表");
        return Result.success(deviceService.listAll());
    }

    /**
     * 新增设备
     * @param device 设备
     * @return 新增成功
     */
    @PostMapping("/add")
    @Operation(summary = "新增设备")
    public Result<String> add(@RequestBody Device device) {
        log.info("新增设备:{}", device);
        deviceService.add(device);
        return Result.success("新增成功");
    }

    /**
     * 更新设备
     * @param device 设备
     * @return 更新
     */
    @PostMapping("/update")
    @Operation(summary = "修改设备")
    public Result<String> update(@RequestBody Device device) {
        log.info("修改设备:{}", device);
        deviceService.update(device);
        return Result.success("更新成功");
    }

    /**
     * 删除设备
     * @param id 设备id
     * @return 删除成功
     */
    @PostMapping("/delete")
    @Operation(summary = "删除设备")
    public Result<String> delete(Long id) {
        log.info("删除设备:{}", id);
        deviceService.removeById(id);
        return Result.success("删除成功");
    }

    /**
     * 修改设备状态
     * @param id 设备id
     * @param status 状态
     * @return 成功
     */
    @PostMapping("/status")
    @Operation(summary = "修改设备状态")
    public Result<String> changeStatus(Long id, Integer status) {
        log.info("修改设备状态:{},{}", id, status);
        deviceService.changeStatus(id, status);
        return Result.success("状态更新成功");
    }
}