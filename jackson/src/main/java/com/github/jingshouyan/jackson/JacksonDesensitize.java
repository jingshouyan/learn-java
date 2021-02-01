package com.github.jingshouyan.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * 脱敏
 *
 * @author jingshouyan
 * 2020-11-20 14:38
 **/
@NoArgsConstructor
public class JacksonDesensitize extends JsonSerializer<String> implements ContextualSerializer {

    private static final Mask MASK = new Mask();

    @Override
    public void serialize(String s, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(s);
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty p) throws JsonMappingException {
        String name = p.getName();
        AnnotatedMember member = p.getMember();
        String className = member.getDeclaringClass().getCanonicalName();
        String fullName = className + "." +name;
        if(SET.contains(fullName)){
            return MASK;
        }
        return this;
    }

    private static final Set<String> SET = new HashSet<>();
    static {
        SET.add("com.github.jingshouyan.jackson.ExtendableBean.name2");
        SET.add("com.github.jingshouyan.jackson.ItemWithRef.itemName");
    }

    private static class Mask extends JsonSerializer<String> {

        @Override
        public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeString("***");
        }
    }
}
