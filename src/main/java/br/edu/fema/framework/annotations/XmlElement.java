package br.edu.fema.framework.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface XmlElement {
    String value() default "";
    boolean enabled() default true;
    String fieldAttribute() default "";
    String itemsName() default "";
    String dateFormat() default "";
}
