package Annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)

/** this a custom annotation for methods
  */

public @interface MethodDocumentation {
    String value() default "";

}
