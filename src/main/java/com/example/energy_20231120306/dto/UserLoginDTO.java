package com.example.energy_20231120306.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "用户登录请求对象")
public class UserLoginDTO implements Serializable {

    @Schema(description = "用户名", example = "1224342")
    private String username;

    @Schema(description = "密码",example = "123456")
    private String password;

}
