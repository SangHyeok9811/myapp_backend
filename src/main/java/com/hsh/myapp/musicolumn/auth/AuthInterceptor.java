package com.hsh.myapp.musicolumn.auth;

import com.hsh.myapp.musicolumn.auth.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.lang.reflect.Method;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Autowired
    JwtUtil jwt;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
        if(handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            Auth auth = method.getAnnotation(Auth.class);

            if(auth == null) {
                return true;
            }

            String token = request.getHeader("Authorization");


            if(token == null || token.isEmpty()) {
                response.setStatus(401);
                return false;
            }

            AuthUser authUser = jwt.validateToken(token.replace("Bearer ",""));
            if(authUser == null) {
                response.setStatus(401);
                return false;
            }


            request.setAttribute("authUser", authUser);
            return true;
        }
        return true;
    }
}
