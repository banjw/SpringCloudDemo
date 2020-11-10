package com.learn.demo.controller;

import com.learn.demo.entities.User;
import com.learn.demo.exception.ControllerAPIException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * UserController
 *
 * @author banjiawei
 * @date 2020/11/10
 */
@RestController
public class UserController {

    @PostMapping("/user/addUser/globalAdvice")
    public String addUserGlobalAdvice(@RequestBody @Valid User user){
        try {
            int a = 1/0;
        } catch (Exception e){
            throw new ControllerAPIException(e.getMessage());
        }
        return "OK";
    }
}
