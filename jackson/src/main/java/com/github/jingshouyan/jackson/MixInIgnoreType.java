package com.github.jingshouyan.jackson;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * mixin
 *
 * @author jingshouyan
 * 2020-11-17 17:13
 **/
@JsonIgnoreProperties("name")
public class MixInIgnoreType {
}
