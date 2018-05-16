package com.meancat.polyjackson.messages;

import com.fasterxml.jackson.annotation.JsonTypeName;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@JsonTypeName
public @interface CustomRequest {
}
