package com.windaka.suizhi.zjj.constants;

/**
 * 返回请求响应常量
 * @author: hjt
 */
public class ReturnConstants {
    public final static String STATUS_SUCCESS = "1000";         //成功

    public final static String STATUS_FAILED = "0000";          //失败

    public final static String STATUS_TOKEN_FAILED = "9999";    //TOKEN失效

    public final static String CODE_LOGIN_SUCESS = "0100";      //登录成功

    public final static String CODE_LOGIN_FAILEED_USER_OR_PWD_BAD = "0101";      //用户名或密码错误

    public final static String CODE_LOGIN_FAILEED_VERIFYCODE_BAD = "0102";      //验证码错误

    public final static String CODE_ADD_USER_BAD = "0103";      //用户新增失败：用户名已存在

    public final static String CODE_SUCESS = "1000";            //成功

    public final static String CODE_FAILED = "0000";            //失败

    public final static String MSG_SUCESS = "请求成功";

    public final static String MSG_FAILED = "请求失败";

    public final static String MSG_LOGIN_SUCESS = "登录成功";

    public final static String MSG_LOGIN_FAILED = "登录失败";

    public final static String MSG_CLIENT_SUCESS = "微服务调用成功";

    public final static String MSG_CLIENT_FAILED = "微服务调用失败";

    public final static String MSG_VERIFY_CODE_IS_NULL = "请输入验证码";

    public final static String MSG_VERIFY_CODE_CREATE_FAILED = "验证码渲染失败";

    public final static String MSG_LOGIN_USERNAME_IS_NULL = "请输入用户名";

    public final static String MSG_LOGIN_PASSWORD_IS_NULL = "请输入密码";

    public final static String MSG_LOGIN_FAILEED_USER_OR_PWD_BAD = "用户名或密码错误";

    public final static String MSG_LOGIN_FAILED_VERIFYCODE_BAD = "验证码错误";

    public final static String MSG_OPER_SUCCESS = "操作成功";

    public final static String MSG_OPER_FAILED = "操作失败";

    public final static String MSG_QUERY_SUCCESS = "查询成功";

    public final static String MSG_QUERY_FAILED = "查询失败";

    public final static String MSG_TOKEN_FAILED = "登录超时，请重新登录";



}
