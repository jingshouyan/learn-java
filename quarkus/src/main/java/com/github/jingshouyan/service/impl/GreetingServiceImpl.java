package com.github.jingshouyan.service.impl;

import com.github.jingshouyan.service.GreetingService;

import javax.enterprise.context.ApplicationScoped;

/**
 * @author jingshouyan
 * #date 2020/6/29 9:39
 */
@ApplicationScoped
public class GreetingServiceImpl implements GreetingService {

    @Override
    public String greeting(String name) {
        return "hello " + name;
    }
}
