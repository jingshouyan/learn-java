package spring.enable;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.Assert;
import spring.enable.client.JrpcClient;

import java.lang.reflect.*;
import java.util.Arrays;

/**
 * @author jingshouyan
 * #date 2019/9/17 17:46
 */

public class JrpcServiceFactoryBean implements FactoryBean<Object>, InitializingBean, ApplicationContextAware {
    @Getter
    @Setter
    private Class<?> type;

    private ApplicationContext ctx;
    @Getter
    @Setter
    private String server;
    @Getter
    @Setter
    private String version;
    @Getter
    @Setter
    private String alias;

    @Override
    public void afterPropertiesSet() {
        Assert.hasText(this.server, "Server must be set");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.ctx = applicationContext;
    }

    @Override
    public Class<?> getObjectType() {
        return type;
    }

    @Override
    public Object getObject() throws Exception {
        return getTarget();
    }

    @SuppressWarnings("unchecked")
    <T> T getTarget() {
        JrpcClient jrpcClient = ctx.getBean(JrpcClient.class);
        T t = (T) Proxy.newProxyInstance(
                type.getClassLoader(), new Class[]{type},
                (Object proxy, Method method, Object[] args) -> {
                    if("toString".equals(method.getName())){
                        return alias + "$Proxy";
                    }
                    if("hashCode".equals(method.getName())){
                        return alias.hashCode();
                    }
                    jrpcClient.test();
                    Type type = method.getGenericReturnType();
                    if (type == void.class) {
                        System.out.println("return type: void");
                    } else {
                        System.out.println("return type is " + type);
                    }
                    int mod = method.getModifiers();
                    if (Modifier.isAbstract(mod)) {
                        System.out.println(method.toGenericString() + "is abstract");
                    }
                    if (method.isDefault()) {
                        return method.invoke(proxy, args);
                    }
                    if (method.getDeclaringClass() != Object.class) {
                        //实现业务逻辑,比如发起网络连接，执行远程调用，获取到结果，并返回
                        System.out.println(method.getName() + " method invoked ! param: " + Arrays.toString(args));

                    }
                    return null;
                }
        );
        return t;
    }
}
