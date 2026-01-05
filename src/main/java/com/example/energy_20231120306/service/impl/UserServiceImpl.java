package com.example.energy_20231120306.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.energy_20231120306.dto.UserLoginDTO;
import com.example.energy_20231120306.dto.UserRegisterDTO;
import com.example.energy_20231120306.entity.User;
import com.example.energy_20231120306.exception.BaseException;
import com.example.energy_20231120306.mapper.UserMapper;
import com.example.energy_20231120306.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    /**
     * 用户登录
     * @param userLoginDTO 登录表单
     * @return user对象
     */
    @Override
    public User login(UserLoginDTO userLoginDTO) {
        String username = userLoginDTO.getUsername();
        String password = userLoginDTO.getPassword();


        List<User> users = list(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
        if (users.isEmpty()) {
            throw new BaseException("账户不存在");
        }
        User user = users.getFirst();

        if (user == null) {
            throw new BaseException("账户不存在");
        }

        // MD5 加密前端密码并对比
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!password.equals(user.getPassword())) {
            throw new BaseException("密码错误");
        }


        return user;
    }

    @Override
    @Transactional
    public void register(UserRegisterDTO userRegisterDTO) {
        // 参数不能为空
        if (userRegisterDTO == null) {
            throw new BaseException("参数不能为空");
        }

        String username = userRegisterDTO.getUsername();
        String password = userRegisterDTO.getPassword();
        String rePassword = userRegisterDTO.getRePassword();

        // 校验字段不能为空
        if (username == null || username.isEmpty() ||
                password == null || password.isEmpty() ||
                rePassword == null || rePassword.isEmpty()) {
            throw new BaseException("用户名或密码不能为空");
        }

        // 判断用户名是否已存在
        User existingUser = getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username), false);
        if (existingUser != null) {
            throw new BaseException("账户已存在");
        }

        // 判断两次密码是否一致
        if (!password.equals(rePassword)) {
            throw new BaseException("两次密码不同");
        }

        // 构建 User 对象
        User user = new User();
        BeanUtils.copyProperties(userRegisterDTO, user);

        // MD5 加密密码
        user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        user.setRole(2);
        // 保存到数据库
        save(user);
    }

    @Override
    public User getByUsername(String username) {
        return getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username), false);
    }

}
