package com.github.jingshouyan.forkjoin;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author jingshouyan
 * #date 2019/8/21 14:30
 */

public class StringAction extends BaseRecursiveAction<String> {
    public static final int THRESHOLD = 10;
    public static final Consumer<String> CONSUMER = (str) -> System.out.println(str);

    public StringAction(List<String> strings){
        super(strings,CONSUMER,THRESHOLD);
    }

}
