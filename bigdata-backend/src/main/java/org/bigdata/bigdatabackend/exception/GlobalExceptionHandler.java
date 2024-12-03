package org.bigdata.bigdatabackend.exception;


import org.bigdata.bigdatabackend.vo.ResultVO;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = BigDataException.class)
    public ResultVO<String> handleAIExternalException(BigDataException e) {
        e.printStackTrace();
        return ResultVO.buildFailure(e.getMessage());
    }
}
