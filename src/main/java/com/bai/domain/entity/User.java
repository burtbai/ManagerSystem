package com.bai.domain.entity;

import lombok.Data;

import java.util.List;

/**
 * @author burtbai
 */
@Data
public class User {

    private int userId;

    private List<String> endpoint;
}
