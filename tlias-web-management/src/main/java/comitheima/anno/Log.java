package comitheima.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//基于注解的aop
@Target(ElementType.METHOD)//加在方法上
@Retention(RetentionPolicy.RUNTIME)//且在运行时生效
public @interface Log {

}
