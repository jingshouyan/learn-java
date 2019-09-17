package spring.enable;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spring.enable.serive.UserService;

/**
 * @author jingshouyan
 * #date 2019/9/17 23:03
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
@Slf4j
public class TestEnable {

    @Autowired
    private UserService userService;

    @Test
    public void test(){
        userService.getUser("abc");
    }
}
