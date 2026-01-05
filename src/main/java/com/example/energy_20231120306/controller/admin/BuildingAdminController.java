package com.example.energy_20231120306.controller.admin;

import com.example.energy_20231120306.entity.Building;
import com.example.energy_20231120306.result.Result;
import com.example.energy_20231120306.service.BuildingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/admin/building")
@Tag(name = "管理员端建筑管理接口")
@Slf4j
public class BuildingAdminController {

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

    /**
     * 新增建筑
     * @param building 建筑
     * @return 成功
     */
    @PostMapping("/add")
    @Operation(summary = "新增建筑")
    public Result<String> add(@RequestBody Building building) {
        log.info("新增建筑：{}", building);

        building.setId(null);
        building.setCreateTime(LocalDateTime.now());
        buildingService.save(building);
        return Result.success("新增成功");
    }

    /**
     * 修改建筑物信息
     * @param building 建筑物实体
     * @return 成功
     */
    @PostMapping("/update")
    @Operation(summary = "修改建筑信息")
    public Result<String> update(@RequestBody Building building) {
        log.info("更新建筑：{}", building);

        buildingService.updateById(building);
        return Result.success("更新成功");
    }

    /**
     * 删除建筑物
     * @param id 建筑物id
     * @return 成功
     */
    @PostMapping("/delete")
    @Operation(summary = "删除建筑")
    public Result<String> delete(@RequestParam Long id) {
        log.info("删除建筑：{}", id);

        buildingService.removeById(id);
        return Result.success("删除成功");
    }
}
