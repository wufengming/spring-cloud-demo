package com.wfm.servicesystem.common.aop;

import com.wfm.servicecommon.aspect.AutoLog;
import com.wfm.servicecommon.utils.IpUtil;
import com.wfm.servicecommon.utils.JacksonUtil;
import com.wfm.servicesystem.entity.SysLog;
import com.wfm.servicesystem.service.SysLogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * description: 系统日志，切面处理类
 * date: 2019-12-17 17:40
 *
 * @author: wfm
 * @version: 1.0
 */
// 使用@Aspect注解声明一个切面
@Aspect
@Component
public class LogAop {


    @Autowired
    private SysLogService sysLogService;
    /**
     * 这里我们使用注解的形式
     * 当然，我们也可以通过切点表达式直接指定需要拦截的package,需要拦截的class 以及 method
     * 切点表达式:   execution(...)
     */
    //@Pointcut("execution(* *..controller..*Controller*.*(..))")
    @Pointcut("@annotation(com.wfm.servicecommon.aspect.AutoLog)")
    public void logPointCut() {

    }

    /**
     * 环绕通知 @Around  ， 当然也可以使用 @Before (前置通知)  @After (后置通知)
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = point.proceed();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;

        //保存日志
        saveSysLog(point, time);

        return result;
    }

    /**
     * 保存日志
     * @param joinPoint
     * @param time
     */
    private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        SysLog sysLog = new SysLog();
        AutoLog syslog = method.getAnnotation(AutoLog.class);
        if (syslog != null) {
            //注解上的描述,操作日志内容
            sysLog.setLogContent(syslog.value());
            sysLog.setLogType(syslog.logType());

        }

        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");


        //请求的参数
        Object[] args = joinPoint.getArgs();
        try {
            String params = JacksonUtil.BeanToJson(args);
            sysLog.setRequestParam(params);
        } catch (Exception e) {

        }

        //获取request
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //设置IP地址
        sysLog.setIp(IpUtil.getRequestIp(request));

        //获取登录用户信息
//        LoginUser sysUser = (LoginUser)SecurityUtils.getSubject().getPrincipal();
//        if(sysUser!=null){
//            sysLog.setUserid(sysUser.getUsername());
//            sysLog.setUsername(sysUser.getRealname());
//
//        }
        //耗时
        sysLog.setCostTime(time);


        //保存系统日志
        sysLogService.save(sysLog);


    }
}
