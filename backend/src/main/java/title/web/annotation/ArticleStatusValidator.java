package title.web.annotation;

import title.web.annotation.ArticleStatus;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * 文章状态校验器 - 实现ArticleStatus注解的校验逻辑
 */
public class ArticleStatusValidator implements ConstraintValidator<ArticleStatus, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        return value.equals("已发布") || value.equals("草稿");
    }
}