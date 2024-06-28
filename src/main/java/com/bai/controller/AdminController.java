package com.bai.controller;

import com.bai.constant.CommonConstant;
import com.bai.core.interceptor.UserContext;
import com.bai.domain.Result;
import com.bai.domain.request.AddUserRequest;
import com.bai.service.IUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author burtbai
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private IUserService userService;

    @PostMapping("/addUser")
    public Result addUser(@RequestAttribute(CommonConstant.REQUEST_ATTRIBUTE_CURRENT_USER) UserContext currentUser,
                          @RequestBody AddUserRequest addUserRequest) throws IOException {
        if (!CommonConstant.ADMIN_ROLE_NAME.equals(currentUser.getRole())) {
            return Result.builder().code(403).message("Forbidden").build();
        }

        userService.addUser(addUserRequest);

        return Result.builder().code(200).message("success").build();
    }

}
