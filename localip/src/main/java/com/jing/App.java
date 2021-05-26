package com.jing;

import java.net.UnknownHostException;

/**
 * test localip
 *
 * @author jingshouyan
 * 2021-03-03 20:08
 **/
public class App {

    public static final String ENV_LOCAL_IP = "LOCAL_IP";

    public static void main(String[] args) {
        while (true) {
            try {
                System.out.println("环境变量 " + ENV_LOCAL_IP + ":" + getEnvIp());
                System.out.println("主机是否为Windows系统：" + LocalHostUtil.isWindowsOS());
                System.out.println("主机名称：" + LocalHostUtil.getHostName());
                System.out.println("系统首选IP：" + LocalHostUtil.getLocalIP());
                System.out.println("系统所有IP：" + String.join(",", LocalHostUtil.getLocalIPs()));
                Thread.sleep(1000);
            } catch (UnknownHostException e) {
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private static String getEnvIp() {
        return System.getenv().get(ENV_LOCAL_IP);
    }
}
