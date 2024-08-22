package com.github.jingshouyan.map;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class MapTest<T> {
    List<T> ts;
    Map<String,User> userMap;


    public static void main(String[] args) {
        MapTest<User> mapTest = new MapTest<>();
        User u = mapTest.getUserMap().get("1");
    }
}
