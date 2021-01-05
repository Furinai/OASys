package cn.linter.oasys.common.handler;

import cn.linter.oasys.common.entity.Result;
import cn.linter.oasys.common.entity.ResultStatus;
import cn.linter.oasys.common.exception.BusinessException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 全局异常处理器
 *
 * @author wangxiaoyang
 * @date 2020/12/19
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 业务异常处理器
     */
    @ExceptionHandler(BusinessException.class)
    public ResultStatus businessExceptionHandler(BusinessException e) {
        return e.getStatus();
    }

    /**
     * 非法参数异常处理器
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public Result<String> illegalArgumentExceptionHandler(Exception e) {
        return Result.of(ResultStatus.ARGUMENT_NOT_VALID, e.getMessage());
    }

    /**
     * 参数赋值异常处理器
     */
    @ExceptionHandler(BindException.class)
    public Result<List<String>> bindExceptionHandler(BindException e) {
        return Result.of(ResultStatus.ARGUMENT_NOT_VALID, e.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList()));
    }

    /**
     * 参数无效异常处理器
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<List<String>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        return Result.of(ResultStatus.ARGUMENT_NOT_VALID, e.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList()));
    }

}
