package com.nzy.asmdemo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author niezhiyang
 * since 2020/12/14
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD )
@interface DebugLog {
}
