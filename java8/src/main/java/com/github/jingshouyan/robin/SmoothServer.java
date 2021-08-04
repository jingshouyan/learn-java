package com.github.jingshouyan.robin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jingshouyan
 * 2021-08-03 16:31
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SmoothServer {

    private String name;
    private int weight;
    private int curWeight;

    public void incCurWeight() {
        curWeight += weight;
    }

    public void decCurWeight(int weight) {
        curWeight -= weight;
    }

}
