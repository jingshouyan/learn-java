package com.github.jingshouyan;

public class Main {
    public static void main(String[] args) {
        int cpuCount = Runtime.getRuntime().availableProcessors();
        System.out.println("cpu count is " + cpuCount);
    }
}