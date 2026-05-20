package comitheima.exception;


import comitheima.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/*
 * 全局异常处理
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public Result handleException(Exception e){
        log.error("捕获异常",e);
        return Result.error("出错啦，请联系管理员");
    }
    @ExceptionHandler
    public Result handleDuplicateKeyException(DuplicateKeyException e){
        log.error("捕获异常",e);
        String message = e.getMessage();
        int i = message.indexOf("Duplicate entry");
        String errMsg=message.substring(i);
        String duplicateKey= errMsg.split(" ")[2];
        return Result.error(duplicateKey+"已存在");
    }
}
