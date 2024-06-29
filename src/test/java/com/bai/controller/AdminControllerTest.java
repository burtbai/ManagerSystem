package com.bai.controller;

import com.bai.constant.CommonConstant;
import com.bai.domain.request.AddUserRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasProperty;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author burtbai
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class AdminControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @AfterEach
    void tearDown() {
        File file = new File(CommonConstant.DATABASE_FILE_PATH);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    void addUser() throws Exception {
        List<String> list = new ArrayList<>();
        list.add("aaa");
        AddUserRequest addUserRequest = AddUserRequest.builder().userId(1).endpoint(list).build();

        // {"userId":123456,"accountName":"admin1","role":"admin"}
        RequestBuilder requestBuilder = post("/admin/addUser")
                .header(CommonConstant.HEADER_USER_INFO, "eyJ1c2VySWQiOjEyMzQ1NiwiYWNjb3VudE5hbWUiOiJhZG1pbjEiLCJyb2xlIjoiYWRtaW4ifQo=")
                .characterEncoding(StandardCharsets.UTF_8)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(addUserRequest));

        mockMvc.perform(requestBuilder)
                .andExpect(request().attribute(CommonConstant.REQUEST_ATTRIBUTE_CURRENT_USER, hasProperty("userId", Matchers.equalTo(123456))))
                .andExpect(request().attribute(CommonConstant.REQUEST_ATTRIBUTE_CURRENT_USER, hasProperty("role", Matchers.equalTo(CommonConstant.ROLE_NAME_ADMIN))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andDo(print())
                .andReturn();
    }

    @Test
    void addUser_1() throws Exception {
        List<String> list = new ArrayList<>();
        list.add("aaa");
        AddUserRequest addUserRequest = AddUserRequest.builder().userId(1).endpoint(list).build();

        // {"userId":123456,"accountName":"admin1","role":"user"}
        RequestBuilder requestBuilder = post("/admin/addUser")
                .header(CommonConstant.HEADER_USER_INFO, "eyJ1c2VySWQiOjEyMzQ1NiwiYWNjb3VudE5hbWUiOiJhZG1pbjEiLCJyb2xlIjoidXNlciJ9Cg==")
                .characterEncoding(StandardCharsets.UTF_8)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(addUserRequest));

        mockMvc.perform(requestBuilder)
                .andExpect(request().attribute(CommonConstant.REQUEST_ATTRIBUTE_CURRENT_USER, hasProperty("role", Matchers.equalTo(CommonConstant.ROLE_NAME_USER))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(403))
                .andDo(print())
                .andReturn();
    }
}