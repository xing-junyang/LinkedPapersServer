package org.bigdata.bigdatabackend.exception;



public class BigDataException extends RuntimeException{
    public BigDataException(String message) {
        super(message);
    }
    public static BigDataException phoneAlreadyExists() {
        return new BigDataException("手机号已经存在!");
    }
    public static BigDataException phoneNotFound() {return new BigDataException("手机号不存在!"); }
    public static BigDataException passwordError() {return new BigDataException("密码错误!");}
    public static BigDataException emailAlreadyExists() {return new BigDataException("邮箱已经存在!");}
    public static BigDataException emailNotFound() {return new BigDataException("邮箱不存在!");}
    public static BigDataException userNotFound() {return new BigDataException("用户不存在!");}
    public static BigDataException userAlreadyExists() {return new BigDataException("用户已经存在!");}
    public static BigDataException permissionDenied() {return new BigDataException("权限错误!");}
    public static BigDataException notLogin() {return new BigDataException("未登录!");}
    public static BigDataException paperNotFound() {return new BigDataException("论文不存在!");}
}
