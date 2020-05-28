package com.windaka.suizhi.zjj.LogImpl;

import com.windaka.suizhi.zjj.log.LoginInfo;
import com.windaka.suizhi.zjj.model.LoginAppUser;
import com.windaka.suizhi.zjj.utils.AppUserUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: hjt
 * @Date: 2018/12/20 19:00
 * @Version 1.0
 */
public class LoginInfoImpl implements LoginInfo {
    @Override
    public Map<String, Object> getLoginUserInfo() {
        try {
            Map<String, Object> map = new HashMap<>();
            LoginAppUser loginApptUser = AppUserUtil.getLoginAppUser();
            if (loginApptUser != null) {
                map.put("userId", loginApptUser.getUserId());
                map.put("username", loginApptUser.getUsername());
                /*map.put("areaId", loginApptUser.getAreaId());
                map.put("areaName", loginApptUser.getArea().getAreaName());*/

            }
            return map;
        }catch (Exception e){
            return null;
        }
    }
}
