package title.web.aspect;

import title.web.annotation.Info;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Info注解切面类 - 处理@Info注解标记的方法日志记录
 */
@Aspect
@Component
public class InfoAspect {
    private static final Logger log = LoggerFactory.getLogger(InfoAspect.class);

    @Around("@annotation(com.example.web.annotation.Info)")
    public Object interceptServiceMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        // 1. 获取注解信息和目标方法信息
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method targetMethod = signature.getMethod();
        Info annotation = targetMethod.getAnnotation(Info.class);
        String methodDesc = annotation.desc();
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = targetMethod.getName();
        Object[] methodArgs = joinPoint.getArgs();
        long startTime = System.currentTimeMillis();
        Object result = null;
        try {
            // 2. 执行目标方法
            result = joinPoint.proceed();
            long costTime = System.currentTimeMillis() - startTime;
            log.info(">>>{}执行成功", methodDesc);
            log.info(">>>目标类：{} | 目标方法：{} ", className, methodName);
            log.info("耗时：{}ms | Result：{}", costTime, result);
        } catch (Exception e) {
            // 3. 方法执行异常处理
            long costTime = System.currentTimeMillis() - startTime;
            log.error(">>>执行失败{}", methodDesc);
            log.error(">>>目标类：{} | 目标方法：{} ", className, methodName);
            log.error("耗时：{}ms | 异常信息：{}", costTime, e.getMessage());
            throw e;
        }
        return result;
    }
}