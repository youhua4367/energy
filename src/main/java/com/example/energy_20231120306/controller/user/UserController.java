package com.example.energy_20231120306.controller.user;

import com.example.energy_20231120306.constant.JwtClaimsConstant;
import com.example.energy_20231120306.dto.UserLoginDTO;
import com.example.energy_20231120306.dto.UserRegisterDTO;
import com.example.energy_20231120306.entity.User;
import com.example.energy_20231120306.propertities.JwtProperties;
import com.example.energy_20231120306.result.Result;
import com.example.energy_20231120306.service.UserService;
import com.example.energy_20231120306.utils.JwtUtil;
import com.example.energy_20231120306.vo.UserLoginVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user/user")
@Slf4j
@Tag(name = "用户管理接口", description = "用户管理相关接口")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 用户登录
     * @param userLoginDTO 登录传入对象
     * @return 登录响应对象
     */
    @PostMapping("/login")
    @Operation(summary = "用户登录", description = "登录成功后，返回 token")
    public Result<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDTO) {
        log.info("用户登录：{}", userLoginDTO);

        User user = userService.login(userLoginDTO);

        // 登录成功后，返回 jwt 令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, user.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getUserSecretKey(),
                jwtProperties.getUserTtl(),
                claims,
                user.getUsername()
        );

        UserLoginVO userLoginVO = UserLoginVO.builder()
                .role(user.getRole())
                .token(token)
                .username(user.getUsername())
                .build();

        return Result.success(userLoginVO);
    }

    /**
     * 用户注册
     * @param userRegisterDTO 注册表单
     * @return 响应信息
     */
    @PostMapping("/register")
    @Operation(summary = "用户注册")
    public Result<String> register(@RequestBody UserRegisterDTO userRegisterDTO) {
        log.info("用户注册：{}", userRegisterDTO);
        userService.register(userRegisterDTO);
        return Result.success("注册成功!");
    }
}
