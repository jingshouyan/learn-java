package spring.enable;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface JrpcService {

    String server();
    String version() default "";
}
