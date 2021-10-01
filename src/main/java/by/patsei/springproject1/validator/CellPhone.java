package by.patsei.springproject1.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.*;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Documented
@Constraint(validatedBy = PhoneValidator.class)
@Target ({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CellPhone {

    String message() default "{Phone}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
