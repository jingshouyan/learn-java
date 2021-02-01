package com.github.jingshouyan.learn.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 29017
 */
@RestController
public class HelloController {

    @GetMapping("")
    public String index() {
        return "Hello world";
    }

}
