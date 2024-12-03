package org.bigdata.bigdatabackend.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResultVO<T> implements Serializable {

    private Integer code;

    private String message;

    private T data;

    public static <T> ResultVO<T> buildSuccess(T result) {
        return new ResultVO<>(0, "SUCCESS", result);
    }

    public static <T> ResultVO<T> buildFailure(String msg) {
        return new ResultVO<>(-1, msg, null);
    }
}