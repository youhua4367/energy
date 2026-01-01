package com.example.energy_20231120306.interceptor;


import com.example.energy_20231120306.constant.JwtClaimsConstant;
import com.example.energy_20231120306.context.ThreadContext;
import com.example.energy_20231120306.propertities.JwtProperties;
import com.example.energy_20231120306.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * jwt 令牌校验
 */
@Component
@Slf4j
public class JwtTokenInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtProperties jwtProperties;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 判断当前拦截到的是 Controller 的方法还是其他资源
        if (!(handler instanceof HandlerMethod)) {
            // 拦截到的不是方法，放行
            return true;
        }


        // 请求头获取令牌
        String token = request.getHeader(jwtProperties.getUserTokenName());
        log.info("处理前的token:{}", token);
        if (token == null || token.isEmpty()) {
            response.setStatus(401);
            return false;
        }
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        // 校验令牌
        try {
            log.info("JWT Token:{} ", token);
            Claims claims = JwtUtil.parseJWT(jwtProperties.getUserSecretKey(), token);
            String userId = claims.get(JwtClaimsConstant.USER_ID, String.class);
            log.info("JWT UserId:{} ", userId);
            ThreadContext.setCurrentId(userId);
        } catch (Exception e) {
            response.setStatus(401);
            return false;
        }

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception ex) {
        ThreadContext.removeCurrentId();
    }

}
