package cn.albumen.library.annotation;

import java.lang.annotation.*;

/**
 * @author Albumen
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ControllerLog {
    /**
     * 描述
     */
    String description() default "";
    String level();
}