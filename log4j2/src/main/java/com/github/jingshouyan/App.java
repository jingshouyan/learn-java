package com.github.jingshouyan;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.ThreadContext;

/**
 * @author jingshouyan
 * 2021-12-15 16:20
 */
@Slf4j
public class App {

    public static void main(String[] args) {

        ThreadContext.put("user", "${ctx:user1:-${ctx:user}}");
//        MDC.put("traceId", "${ctx:user}");
        String name = "${java:os}";
        String des = "${ctx:loginId}";
        log.info("name is : {}, {}", name, des);
        int m1 = 0;
        int m2 = 64;
        int m3 = 128;
        int m4 = 192;
        int m5 = 224;
        System.out.println(Integer.toBinaryString(m1));
        System.out.println(Integer.toBinaryString(m2));
        System.out.println(Integer.toBinaryString(m3));
        System.out.println(Integer.toBinaryString(m4));
        System.out.println(Integer.toBinaryString(m5));


    }
}
