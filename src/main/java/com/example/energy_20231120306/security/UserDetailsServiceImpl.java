package com.example.energy_20231120306.security;

import com.example.energy_20231120306.entity.User;
import com.example.energy_20231120306.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    /**
     * 获取用户
     * @param username 用户名
     * @return 用户
     * @throws UsernameNotFoundException 异常
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByUsername(username);
        if (user == null) throw new UsernameNotFoundException("用户不存在");
        return new UserDetailsImpl(user);
    }
}
