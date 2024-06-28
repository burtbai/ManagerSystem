package com.bai.domain.request;

import lombok.Data;

import java.util.List;

/**
 * @author burtbai
 */
@Data
public class AddUserRequest {

    private int userId;

    private List<String> endpoint;
}
