package com.bai.core.interceptor;

import com.bai.constant.CommonConstant;
import com.bai.domain.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Base64;

/**
 * @author burtbai
 */
@Component
public class UserInfoInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String headerUserInfo = request.getHeader("user-info");

        try {
            byte[] userInfoBytes = Base64.getDecoder().decode(headerUserInfo);
            UserContext user = new ObjectMapper().readValue(userInfoBytes, UserContext.class);
            request.setAttribute(CommonConstant.REQUEST_ATTRIBUTE_CURRENT_USER, user);
        } catch (Exception e) {
            Result result = Result.builder().code(500).message(e.getMessage()).build();
            String json = new ObjectMapper().writeValueAsString(result);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().println(json);
            return false;
        }

        return true;
    }
}
