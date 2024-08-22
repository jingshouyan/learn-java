package com.github.jingshouyan.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * test
 *
 * @author jingshouyan
 * 2020-11-16 20:14
 **/

public class TestJson {


    @SneakyThrows
    @Test
    public void t3() {
        UserWithRef user = new UserWithRef(1, "John", new ArrayList<>());
        ItemWithRef item = new ItemWithRef(2, "book", user);
        ItemWithRef item2 = new ItemWithRef(3, "book2", user);
        user.getItems().add(item);
        user.getItems().add(item2);
        ObjectMapper mapper = new ObjectMapper();
        mapper.addMixIn(UserWithRef.class, MixInIgnoreType.class);
        String result = mapper.writeValueAsString(item);
        System.out.println(result);
    }


    @Test
    @SneakyThrows
    public void t2() {
        UserWithRef user = new UserWithRef(1, "John", new ArrayList<>());
        ItemWithRef item = new ItemWithRef(2, "book", user);
        ItemWithRef item2 = new ItemWithRef(3, "book2", user);
        user.getItems().add(item);
        user.getItems().add(item2);
        String result = new ObjectMapper().writeValueAsString(item);

        ItemWithRef item1 = new ObjectMapper().readValue(result, ItemWithRef.class);

        System.out.println(result);

        System.out.println(item1);
    }

    @Test
    @SneakyThrows
    public void t1() {
        ExtendableBean bean = new ExtendableBean();
        bean.setName("张三");
        bean.setName2("z2");
        bean.setName3("z3");
        bean.setTypeA(TypeA.A);
        bean.setDate(new Date());
        Map<String, String> properties = new HashMap<>();
        properties.put("gender", "male");
        properties.put("attr1", "swim");
        bean.setProperties(properties);
        ItemWithRef itemWithRef = new ItemWithRef();
        itemWithRef.setId(123);
        itemWithRef.setItemName("张三的包");
        bean.setItemWithRef(itemWithRef);

        ObjectMapper mapper = new ObjectMapper();
        Set<String> exclude = new HashSet<>();
        exclude.add("name");
        PropertyFilter filter = SimpleBeanPropertyFilter.serializeAllExcept(exclude);
        FilterProvider filters = new SimpleFilterProvider().setDefaultFilter(filter);
        mapper.setFilterProvider(filters);
        mapper.addMixIn(Object.class, MixInFilter.class);


        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(String.class, new JacksonDesensitize());
        mapper.registerModule(simpleModule);
        String json = mapper.writeValueAsString(bean);
        System.out.println(json);
        ExtendableBean bean1 = mapper.readValue(json, ExtendableBean.class);
        System.out.println(bean1);
    }


    @Test
    public void test2Bean() throws JsonProcessingException, InterruptedException {
        String json = "{}";
        ObjectMapper mapper = new ObjectMapper();
        Thread.sleep(1000);
        long start = System.currentTimeMillis();
        ExtendableBean bean = mapper.readValue(json, ExtendableBean.class);
        long end = System.currentTimeMillis();

        System.out.println(end - start);

    }

}
