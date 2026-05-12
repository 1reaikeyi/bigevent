package web.excection;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pojo.Result;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(Exception.class)
    public Result exception(Exception e) {
        return Result.error(e.getMessage()+"去联系管理员");
    }
}
