package com.cp1;

import com.github.jingshouyan.spi.UserSpi;

/**
 * @author jingshouyan
 * 2021-12-13 21:36
 */
public class UserSpiImpl implements UserSpi {
    @Override
    public String name() {
        return "cp-1 user spi";
    }
}
