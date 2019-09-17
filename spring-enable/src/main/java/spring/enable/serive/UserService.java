package spring.enable.serive;

import spring.enable.JrpcService;

/**
 * @author jingshouyan
 * #date 2019/9/17 23:05
 */
@JrpcService(server = "user")
public interface UserService {

    void getUser(String userId);
}
