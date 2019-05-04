package com.wllfengshu.security.exception;

/**
 * 自定义异常
 *
 * @author
 */
public class CustomException extends Exception {

    protected ExceptionName exceptionName;

    public enum ExceptionName {
        //没有权限
        Unauthenticated(401),
        //非法参数
        IllegalParam(400),

        //操作失败,错误未知，请联系系统管理员
        OperationFailed(10000),
        //用户名不能为空
        NeedUsername(10001),
        //密码不能为空
        NeedPassword(10002),
        //用户名不存在
        UnknownAccountException(10003),
        //用户名或密码错误
        IncorrectCredentialsException(10004),
        //账户被锁定
        LockedAccountException(10005),
        //过度尝试异常
        ExcessiveAttemptsException(10006),
        //身份验证异常
        AuthenticationException(10007),
        //未登陆，无法操作
        NotLoginError(10008),
        //已登陆，但sessionId不匹配
        LoginButMismatchSessionId(10009)
        ;

        private int code;

        ExceptionName(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

    public CustomException(String message, ExceptionName exceptionName) {
        super(message);
        this.exceptionName = exceptionName;
    }

    public ExceptionName getExceptionName() {
        return exceptionName;
    }
}
