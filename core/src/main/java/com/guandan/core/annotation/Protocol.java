package com.guandan.core.annotation;


import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * @author qixiangyang
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Indexed
public @interface Protocol {
    int value();
}