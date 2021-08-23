package ru.geekbrains.msproducts.controllers;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.*;

import java.util.Date;

@Aspect
@Component
public class AspectProductController {

    @Before("execution(public * ru.geekbrains.msproducts.controllers.ProductController.*(..))") // pointcut expression
    public void beforeAnyMethodInUserDAOClassWithDetails(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println(new Date());
        System.out.println("В ProductController был вызван метод: " + methodSignature);
        Object[] args = joinPoint.getArgs();
        if (args.length > 0) {
            System.out.println("Аргументы:");
            for (Object o : args) {
                System.out.println(o);
            }
        }
    }
}
