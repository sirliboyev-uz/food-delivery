package com.example.fooddeliverybackend.entity.utils.Annotation;

import java.lang.annotation.*;

@Documented
@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface RoleCheckName {
    String value();
}