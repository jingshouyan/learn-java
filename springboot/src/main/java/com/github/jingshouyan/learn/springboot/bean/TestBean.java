package com.github.jingshouyan.learn.springboot.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jingshouyan
 * 2021-12-23 16:08
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestBean<T> {
    private T data;
}
