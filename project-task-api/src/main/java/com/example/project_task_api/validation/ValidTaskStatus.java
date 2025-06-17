package com.example.project_task_api.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = TaskStatusValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidTaskStatus {
    String message() default "Invalid task status";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}