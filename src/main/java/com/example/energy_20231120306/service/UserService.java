package com.example.energy_20231120306.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.energy_20231120306.dto.UserLoginDTO;
import com.example.energy_20231120306.dto.UserRegisterDTO;
import com.example.energy_20231120306.entity.User;

public interface UserService extends IService<User> {
    User login(UserLoginDTO userLoginDTO);

    void register(UserRegisterDTO userRegisterDTO);

    User getByUsername(String username);
}
