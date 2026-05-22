package comitheima.aop;

import comitheima.Utils.CurrentHolder;
import comitheima.anno.Log;
import comitheima.mapper.OperateLogMapper;
import comitheima.pojo.OperateLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.Operator;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class OperationLogAspect {
    @Autowired
    private  OperateLogMapper operateLogMapper;

    @Around("@annotation(comitheima.anno.Log)")
    public Object logOperation(ProceedingJoinPoint joinPoint) throws Throwable {
        long begin=System.currentTimeMillis();

        //执行目标方法
        Object res = joinPoint.proceed();

        long end=System.currentTimeMillis();
        long costTime=end-begin;

        //构建日志实体
        OperateLog log = new OperateLog();
        //获取方法签名
        log.setOperateEmpId(getCurrentUserId());//这里需要你根据实际情况获取当前用户IDlog.setoperateTime(LocalDateTime.now());
        log.setClassName(joinPoint.getTarget().getClass().getName());
        log.setMethodName(joinPoint.getSignature().getName());
        log.setMethodParams(Arrays.toString(joinPoint.getArgs()));
        log.setReturnValue(res != null?res.toString():"void");
        log.setCostTime(costTime);

        operateLogMapper.insert(log);
        return res;

    }

    private Integer getCurrentUserId() {
        return CurrentHolder.getCurrentId();
    }

}
