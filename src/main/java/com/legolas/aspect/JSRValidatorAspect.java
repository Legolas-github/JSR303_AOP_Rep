package com.legolas.aspect;

import javax.validation.constraints.NotNull;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.legolas.domain.Result;

@Aspect
@Component
public class JSRValidatorAspect {
	Logger logger = Logger.getLogger(JSRValidatorAspect.class);
	
	@Pointcut("execution(* com.legolas.controller..*(..)) and @annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public void pointcut() {}
	
	@Before(value="pointcut()")
	public void test() {
		System.out.println("这是aop切面的前置通知");
	}
	
	 /** 
     * 判断验证错误代码是否属于字段为空的情况 
     * @param code 验证错误代码 
     */  
    private boolean isMissingParamsError(String code){  
        if (code.equals(NotNull.class.getSimpleName()) || code.equals(NotBlank.class.getSimpleName()) || code.equals(NotEmpty.class.getSimpleName())){  
            return true;  
        }else{  
            return false;  
        }  
    }  
  
    /** 
     * 切点处理 
     * @param joinPoint 
     * @return 
     * @throws Throwable 
     */
    @Around(value="pointcut()")
    public Object aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {  
        BindingResult result = null;  
        Object[] args = joinPoint.getArgs();  
        if (args != null && args.length != 0){  
            for (Object object : args) {  
                if (object instanceof BindingResult){  
                    result = (BindingResult)object;  
                    break;  
                }  
            }  
        }  
        if (result != null && result.hasErrors()){  
            FieldError fieldError = result.getFieldError();  
            String targetName = joinPoint.getTarget().getClass().getSimpleName();  
            String method = joinPoint.getSignature().getName();  
            logger.info(String.format(
            		"验证失败.控制器:%s, 方法:%s, 参数:%s, 属性:%s, 错误:%s, 消息:%s", 
            		targetName, method, fieldError.getObjectName(), fieldError.getField(), fieldError.getCode(), fieldError.getDefaultMessage()));  
            String firstCode = fieldError.getCode();  
            if (isMissingParamsError(firstCode)){  
                return Result.fail("必选参数丢失");  
            }else{  
                return Result.fail("其他错误");  
            }  
        }  
        return joinPoint.proceed();  
    }  
}
