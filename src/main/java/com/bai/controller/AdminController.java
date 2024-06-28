package com.bai.controller;

import com.bai.domain.Result;
import com.bai.domain.request.AddUserRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author burtbai
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @PostMapping("/addUser")
    public Result addUser(@RequestBody AddUserRequest request) {

        return Result.builder().code(200).message("success").build();
    }

}
