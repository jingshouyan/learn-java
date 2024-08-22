package com.github.jingshouyan.filechannel;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author jingshouyan
 * 2022-08-26 14:32
 */
public class ByteMixTest {


    @Test
    public void testEncode() {
        for (int i = Byte.MIN_VALUE; i <= Byte.MAX_VALUE; i++) {
            ByteMix bx = new ByteMix(i);
            for (int j = Byte.MIN_VALUE; j <= Byte.MAX_VALUE; j++) {
                byte b1 = (byte) j;
                byte b2 = bx.encode(b1);
                byte b3 = bx.decode(b2);
                Assert.assertEquals(b1, b3);
            }
        }
    }
}