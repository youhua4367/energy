package com.example.energy_20231120306.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "用户登录响应对象")
public class UserLoginVO implements Serializable {

    @Schema(description = "用户id", example = "1123324")
    private String username;

    @Schema(description = "用户角色", example = "1")
    private Integer role;

    @Schema(description = "jwt令牌")
    private String token;
}
