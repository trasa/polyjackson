package com.meancat.polyjackson.messages;

import com.fasterxml.jackson.annotation.JsonTypeName;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Classses tagged with this are registered as valid
 * things that could be in a GameMessage.body
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@JsonTypeName
public @interface CustomRequest {
}
