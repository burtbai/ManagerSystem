package com.bai.controller;

import com.bai.constant.CommonConstant;
import com.bai.core.interceptor.UserContext;
import com.bai.domain.Result;
import com.bai.domain.entity.User;
import com.bai.service.IUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author burtbai
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;

    @GetMapping("/{resource}")
    public Result getUserResource(@RequestAttribute(CommonConstant.REQUEST_ATTRIBUTE_CURRENT_USER) UserContext currentUser,
                                  @PathVariable("resource") String resource) throws IOException {
        User user = userService.getUserByUserId(currentUser.getUserId());
        if (user == null
                || user.getEndpoint() == null
                || !user.getEndpoint().contains(resource)) {
            return Result.builder().code(403).message("failure").build();
        }

        return Result.builder().code(200).message("success").build();
    }

}
