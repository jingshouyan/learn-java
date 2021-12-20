package com.github.jingshouyan.learn.springboot.service.impl;

import com.github.jingshouyan.learn.springboot.service.UserService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "vrv.registry.model", havingValue = "zookeeper")
public class UserServiceImpl implements UserService {

    @Override
    public String hello() {
        return "hello UserServiceImpl";
    }
}
