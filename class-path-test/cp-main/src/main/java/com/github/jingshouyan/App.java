package com.github.jingshouyan;

import com.github.jingshouyan.spi.UserSpi;
import com.github.jingshouyan.spi.UserSpiProvider;

import java.util.Collection;

/**
 * @author jingshouyan
 * 2021-12-13 21:30
 */
public class App {

    public static void main(String[] args) {
        Collection<UserSpi> c = UserSpiProvider.all();
        System.out.println("---------");
        c.stream().map(UserSpi::name).forEach(System.out::println);
        System.out.println("---------");
    }
}
