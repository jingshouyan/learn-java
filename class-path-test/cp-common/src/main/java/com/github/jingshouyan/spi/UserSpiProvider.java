package com.github.jingshouyan.spi;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * @author jingshouyan
 * 2021-12-13 21:25
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserSpiProvider {

    private static final Map<String, UserSpi> SPI_MAP = new HashMap<>();

    static {
        ServiceLoader<UserSpi> spiLoader = ServiceLoader.load(UserSpi.class);
        for (UserSpi spi : spiLoader) {
            SPI_MAP.put(spi.name(), spi);
        }
    }

    public static Collection<UserSpi> all() {
        return SPI_MAP.values();
    }
}
