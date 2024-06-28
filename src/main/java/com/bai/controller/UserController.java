package com.bai.controller;

import com.bai.domain.Result;
import com.bai.domain.request.AddUserRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author burtbai
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/{resource}")
    public Result getUserResource(@PathVariable("resource") String resource) {

        return Result.builder().code(200).message("success").build();
    }

}
