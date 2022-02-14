package com.dcy.common.aspect;

import com.alibaba.fastjson.JSON;
import com.dcy.common.constant.CommonConstant;
import com.dcy.common.context.BaseContextHandler;
import com.dcy.common.model.OperationalLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author：dcy
 * @Description: 日志aop
 * @Date: 2019/10/15 9:05
 */
@Aspect
@Slf4j
@Component
public class WebLogAspect {

    private OperationalLog operationalLog = new OperationalLog();
    private static final String LOG_PREFIX = "request-log";
    /**
     * 进入方法时间 获取毫秒数
     */
    private long start;

    /**
     * ..表示包及子包 该方法代表controller层的所有方法
     * Pointcut定义时，还可以使用&&、||、! 这三个运算
     */
    @Pointcut("execution(public * com.dcy.router.controller..*(..))")
    public void controllerMethod() {
    }

    /**
     * 前置通知
     *
     * @param joinPoint
     * @throws Exception
     */
    @Before("controllerMethod()")
    public void logRequestInfo(JoinPoint joinPoint) throws Exception {
        start = System.currentTimeMillis();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            return;
        }
        HttpServletRequest request = attributes.getRequest();
        String userId = request.getHeader(CommonConstant.CONTEXT_KEY_USER_ID);
        String username = request.getHeader(CommonConstant.CONTEXT_KEY_USERNAME);
        String requestURI = request.getRequestURI();
        BaseContextHandler.setUserID(userId);
        BaseContextHandler.setUsername(username);
        BaseContextHandler.setCurrentURL(requestURI);
        operationalLog.setUrl(requestURI);
        operationalLog.setOperName(username);
        operationalLog.setMethod(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        if (joinPoint.getArgs().length == 0) {
            operationalLog.setOperParam("{}");
        } else {
            List<Object> list = new ArrayList<>();
            for (Object arg : joinPoint.getArgs()) {
                // 是否实现校验父接口
                if (arg instanceof ServletRequest || arg instanceof ServletResponse || arg instanceof MultipartFile || arg instanceof MultipartFile[]) {
                    continue;
                }
                list.add(arg);
            }
            operationalLog.setOperParam(JSON.toJSONString(list));
        }
    }


    /**
     * 后置通知
     */
    @AfterReturning(returning = "rvt", pointcut = "controllerMethod()")
    public void logResultVoInfo(Object rvt) throws Exception {
        operationalLog.setExeTime(System.currentTimeMillis() - start);
        operationalLog.setError(null);
        operationalLog.setResult(JSON.toJSONString(rvt));
        BaseContextHandler.remove();
        log.info(LOG_PREFIX + " -=- {}", JSON.toJSONString(operationalLog));
    }

    /**
     * 异常通知
     *
     * @param joinPoint
     * @param exception
     */
    @AfterThrowing(value = "controllerMethod()", throwing = "exception")
    public void doAfterThrowingAdvice(JoinPoint joinPoint, Throwable exception) {
        operationalLog.setExeTime(System.currentTimeMillis() - start);
        operationalLog.setError(exception.getMessage());
        operationalLog.setResult(null);
        BaseContextHandler.remove();
        log.error(LOG_PREFIX + " -=- {}", JSON.toJSONString(operationalLog));
    }
}
