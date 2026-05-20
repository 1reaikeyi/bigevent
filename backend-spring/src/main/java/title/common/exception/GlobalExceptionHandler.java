package title.common.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import title.common.result.Result;

/**
 * 全局异常处理器 - 统一处理应用中的异常
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Result exception(Exception e) {
        return Result.error(e.getMessage() + "去联系管理员");
    }
}