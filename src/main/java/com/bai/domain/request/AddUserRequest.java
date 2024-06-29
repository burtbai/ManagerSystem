package com.bai.domain.request;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author burtbai
 */
@Data
@Builder
public class AddUserRequest {

    private int userId;

    private List<String> endpoint;
}
