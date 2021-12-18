package com.scope.toy.utils.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Aspect
@Component
@Slf4j
public class LogAspect {
    private String mapToString(Map<String, String[]> map) {
        StringBuilder result = new StringBuilder();
        result.append("[ ");
        List<Map.Entry<String, String[]>> entryList = new ArrayList<>(map.entrySet());
        for (Map.Entry<String, String[]> entry : entryList) {
            result.append(entry.getKey());
            result.append(":");
            result.append(Arrays.toString(entry.getValue()));

            result.append(", ");
        }
        result.append(" ]");
        return result.toString();
    }
    //Pointcut 설정 -> 모든 컨트롤러에서!
    @Pointcut("within(com.scope.toy.controller..*)")
    public void onRequest(){}

    //Advice 설정
    @Around("com.scope.toy.utils.aop.LogAspect.onRequest()")
    public Object doLogging(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                        .getRequest();

        //stopWatch로 실행시간 측정
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        log.info("[{}], {}, {}, {}", MDC.get("UUID"), request.getRequestURI(), request.getMethod(), mapToString(request.getParameterMap()));
        Object result = proceedingJoinPoint.proceed(); // controller 로직 실행

        stopWatch.stop();
        log.info("[{}], {}, {}, WorkTime : {} ms", MDC.get("UUID"), request.getRequestURI(), request.getMethod(), stopWatch.getLastTaskTimeNanos()/1000000.0);
        return result;
    }
}
