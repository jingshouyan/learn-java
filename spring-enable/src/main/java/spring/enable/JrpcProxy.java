package spring.enable;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface JrpcProxy {

    String server();
    String version() default "";
}
