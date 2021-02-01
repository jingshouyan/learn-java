package com.github.jingshouyan.jackson;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * d
 *
 * @author jingshouyan
 * 2020-11-16 20:13
 **/
@Data
public class ExtendableBean {
    private String name;

    private String name2;

    private String name3;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private TypeA typeA;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
    private Date date;

    private ItemWithRef itemWithRef;

    @JsonAnySetter
    private Map<String, String> properties = new HashMap<>();

    @JsonAnyGetter
    public Map<String, String> getProperties() {
        return properties;
    }


}
