package com.bai.domain;

import lombok.Builder;
import lombok.Data;

/**
 * @author burtbai
 */
@Data
@Builder
public class Result {

    private Integer code;

    private String message;
}
