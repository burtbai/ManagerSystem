package com.bai.service;

import com.bai.constant.CommonConstant;
import com.bai.domain.entity.User;
import com.bai.domain.request.AddUserRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author burtbai
 */
@SpringBootTest
class UserServiceTest {

    @Resource
    private IUserService userService;

    private static final String filepath = CommonConstant.DATABASE_FILE_PATH;

    @BeforeEach
    void setUp() {
        File file = new File(filepath);
        if (file.exists()) {
            file.delete();
        }
    }

    @AfterEach
    void tearDown() {
        File file = new File(filepath);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    void addUser() throws IOException {
        List<String> list = new ArrayList<>();
        list.add("aaa");
        list.add("bbb");
        AddUserRequest addUserRequest = AddUserRequest.builder().userId(1).endpoint(list).build();
        userService.addUser(addUserRequest);

        File file = new File(filepath);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        StringBuilder content = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            content.append(line).append("\n");
        }

        // TODO: json key 顺序不固定
        assertEquals("{\"userId\":1,\"endpoint\":[\"aaa\",\"bbb\"]}\n", content.toString());
    }

    @Test
    void getUserByUserId() throws IOException {
        File file = new File(filepath);
        if (!file.exists()) {
            file.createNewFile();
        }
        try (FileWriter fileWriter = new FileWriter(filepath)) {
            fileWriter.append("{\"userId\":1,\"endpoint\":[\"aaa\",\"bbb\"]}\n");
        }
        User user = userService.getUserByUserId(1);

        assertNotNull(user);
        assertEquals(1, user.getUserId());
    }

    @Test
    void getUserByUserId_1() throws IOException {
        File file = new File(filepath);
        if (!file.exists()) {
            file.createNewFile();
        }
        try (FileWriter fileWriter = new FileWriter(filepath)) {
            fileWriter.append("{\"userId\":1,\"endpoint\":[\"aaa\",\"bbb\"]}\n");
        }
        User user = userService.getUserByUserId(2);

        assertNull(user);
    }
}