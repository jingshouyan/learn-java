package spring.enable.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author jingshouyan
 * #date 2019/9/21 8:48
 */
@Component
@Data
@ConfigurationProperties(prefix = "test")
public class TestProperties {
    private int abc;
}
