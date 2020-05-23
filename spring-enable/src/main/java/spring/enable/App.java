package spring.enable;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import spring.enable.properties.TestProperties;

/**
 * @author jingshouyan
 * #date 2019/9/17 23:02
 */
@SpringBootApplication
@EnableJrpcServices
@EnableScheduling
@Slf4j
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }

    @Autowired
    private TestProperties testProperties;

    @Scheduled(fixedDelay = 3000)
    public void test(){
        log.info("test.abc = {}",testProperties.getAbc());
        log.info("test = {}",testProperties);
    }
}
