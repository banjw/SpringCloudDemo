package com.learn.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 控制层异常
 *
 * @author banjiawei
 * @date 2020/11/10
 */
@Getter
@AllArgsConstructor
public class ControllerAPIException extends RuntimeException {
    private String msg;
}
