package spring.enable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author jingshouyan
 * #date 2019/9/17 23:02
 */
@SpringBootApplication
@EnableJrpcServices
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }
}
