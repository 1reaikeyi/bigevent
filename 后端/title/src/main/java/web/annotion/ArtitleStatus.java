package web.annotion;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ArtitleStatusValidator.class})
public @interface ArtitleStatus {
    String message() default "文章状态只能是：已发布或草稿";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
