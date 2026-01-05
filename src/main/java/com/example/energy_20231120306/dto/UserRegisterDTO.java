package com.example.energy_20231120306.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "用户注册请求对象")
public class UserRegisterDTO implements Serializable {
    private String username;
    private String password;
    // 确认密码
    private String rePassword;

}