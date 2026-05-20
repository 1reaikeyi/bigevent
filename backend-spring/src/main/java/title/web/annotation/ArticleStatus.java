package title.web.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 文章状态校验注解 - 校验文章状态只能是"已发布"或"草稿"
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ArticleStatusValidator.class})
public @interface ArticleStatus {
    String message() default "文章状态只能是：已发布或草稿";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}