package com.github.jingshouyan.learn.springboot.controller;

import com.github.jingshouyan.learn.springboot.bean.TestBean;
import com.github.jingshouyan.learn.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 29017
 */
@RestController
public class HelloController {
    @Autowired
    private UserService userService;

    @Autowired
    private TestBean<Long> testBean;

    @GetMapping("")
    public String index() {
        return "Hello world!" + testBean.getData();
    }

}
