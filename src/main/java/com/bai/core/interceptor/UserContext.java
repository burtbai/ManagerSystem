package com.bai.core.interceptor;

import lombok.Data;

/**
 * @author burtbai
 */
@Data
public class UserContext {
    private int userId;
    private String accountName;
    private String role;
}
