package by.patsei.springproject1.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Aspect
@Component
@Slf4j
public class LogAspect {

   // @Pointcut("@annotation(LogAnnotation)")
   @Pointcut("execution(public * by.patsei.springproject1.controller.PersonController.*(..))")
    public void callAtPersonController() {
    }

    @Before("callAtPersonController()")
    public void beforeCallMethod(JoinPoint jp) {
        String args = Arrays.stream(jp.getArgs())
                .map(a -> a.toString())
                .collect(Collectors.joining(","));
        log.info("before " + jp.toString() + ", args=[" + args + "]");

    }

    @After("callAtPersonController()")
    public void afterCallAt(JoinPoint jp) {
        log.info("after " + jp.toString());
    }
}
