package com.gabe.dlrvprjgroup.gabedlrvapi.common.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME) // 실행 도중에 적용함
@Service
public @interface Converter {

    @AliasFor(annotation = Service.class)
    String value() default  "";

}
