package com.bai.service;

import com.bai.domain.entity.User;
import com.bai.domain.request.AddUserRequest;

import java.io.IOException;

/**
 * @author burtbai
 */
public interface IUserService {

    void addUser(AddUserRequest addUserRequest) throws IOException;

    User getUserByUserId(int userId) throws IOException;
}
