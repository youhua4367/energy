package com.example.energy_20231120306.controller.user;

import com.example.energy_20231120306.entity.Building;
import com.example.energy_20231120306.result.Result;
import com.example.energy_20231120306.service.BuildingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/building")
@Tag(name = "用户端建筑信息接口")
@Slf4j
public class BuildingUserController {

    @Autowired
    private BuildingService buildingService;

    /**
     * 查询所有建筑
     * @return 建筑列表
     */
    @GetMapping("/list")
    @Operation(summary = "查询所有建筑")
    public Result<List<Building>> list() {
        log.info("查询所有建筑信息");
        return Result.success(buildingService.listAll());
    }
}
