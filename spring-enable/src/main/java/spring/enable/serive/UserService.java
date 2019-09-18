package spring.enable.serive;

import reactor.core.publisher.Mono;
import spring.enable.JrpcService;
import spring.enable.bean.Rsp;
import spring.enable.bean.User;

/**
 * @author jingshouyan
 * #date 2019/9/17 23:05
 */
@JrpcService(server = "user")
public interface UserService {

    void v(String userId);
    User obj(String b);
    Rsp rsp(int a);
    Mono<Rsp> monoRsp(int a);
    Mono<Void> monoVoid(double ff);
    Mono<User> monoUser(String a);


}
