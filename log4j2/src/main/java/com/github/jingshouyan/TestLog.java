package com.github.jingshouyan;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.MessageFactory;

@Log4j2
public class TestLog {

    public static void main(String[] args) {
        TestA testA = new TestA();
        long start = System.currentTimeMillis();
        MyMessage m = new MyMessage(testA);
        MessageFactory factory = log.getMessageFactory();
        log.info("test a {}", testA);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public static class TestA {

        @Override
        public String toString() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "TEST A";
        }
    }

    public static class MyMessage implements Message {

        public MyMessage(Object obj) {
            this.obj = obj;
        }

        private Object obj;

        @Override
        public String getFormattedMessage() {
            return obj.toString();
        }

        @Override
        public String getFormat() {
            return "";
        }

        @Override
        public Object[] getParameters() {
            return new Object[0];
        }

        @Override
        public Throwable getThrowable() {
            return null;
        }
    }
}
