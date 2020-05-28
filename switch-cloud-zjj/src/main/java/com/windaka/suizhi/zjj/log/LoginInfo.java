package com.windaka.suizhi.zjj.log;

import java.util.Map;

/**
 * 登录信息接口
 * <p>如果要继承使用日志在中心必须实现此接口</p>
 * @author: hjt
 * @Date: 2018/12/20 08:45
 * @Version 1.0
 */
public interface LoginInfo {

    /**
     * 获取登录用户信息
     * @return
     */
    Map<String,Object> getLoginUserInfo();
}
