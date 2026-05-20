package title.web.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 方法日志注解 - 用于标记需要记录日志的方法
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Info {
    /**
     * 自定义属性：方法描述（可选，用于日志展示）
     */
    String desc() default "未描述";

    /**
     * 自定义属性：是否记录耗时（默认记录）
     */
    boolean recordCostTime() default true;
}