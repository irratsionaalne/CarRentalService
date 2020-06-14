/*
package CarRentalService.crs.controllers;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

public @interface Valid {
}
*/


package javax.validation;

        import static java.lang.annotation.ElementType.CONSTRUCTOR;
        import static java.lang.annotation.ElementType.FIELD;
        import static java.lang.annotation.ElementType.METHOD;
        import static java.lang.annotation.ElementType.PARAMETER;
        import static java.lang.annotation.ElementType.TYPE_USE;
        import static java.lang.annotation.RetentionPolicy.RUNTIME;

        import java.lang.annotation.Documented;
        import java.lang.annotation.Retention;
        import java.lang.annotation.Target;

/**
 * Marks a property, method parameter or method return type for validation cascading.
 * <p>
 * Constraints defined on the object and its properties are be validated when the
 * property, method parameter or method return type is validated.
 * <p>
 * This behavior is applied recursively.
 *
 * @author Emmanuel Bernard
 * @author Hardy Ferentschik
 */
@Target({ METHOD, FIELD, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Documented
public @interface Valid {
}
