package com.bai.service.impl;

import com.bai.domain.entity.User;
import com.bai.domain.request.AddUserRequest;
import com.bai.service.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author burtbai
 */
@Service
public class UserServiceImpl implements IUserService {
    private static final String filepath = "./user_table.txt";

    @Override
    public void addUser(AddUserRequest addUserRequest) throws IOException {
        File file = new File(filepath);
        if (!file.exists()) {
            file.createNewFile();
        }
        try (FileWriter fileWriter = new FileWriter(filepath, true)) {
            String json = new ObjectMapper().writeValueAsString(addUserRequest);
            fileWriter.append(json + "\n");
        }
    }

    @Override
    public User getUserByUserId(int userId) throws IOException {
        File file = new File(filepath);
        if (!file.exists()) {
            return null;
        }

        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line = "";
        ObjectMapper objectMapper = new ObjectMapper();
        while ((line = bufferedReader.readLine()) != null) {
            User user = objectMapper.readValue(line, User.class);
            if (userId == user.getUserId()) {
                return user;
            }
        }

        return null;
    }
}
