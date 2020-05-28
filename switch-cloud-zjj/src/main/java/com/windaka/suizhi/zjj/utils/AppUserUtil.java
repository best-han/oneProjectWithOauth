package com.windaka.suizhi.zjj.utils;

import com.alibaba.fastjson.JSONObject;
import com.windaka.suizhi.zjj.model.LoginAppUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppUserUtil {

    /**
     * 获取登陆的 LoginAppUser
     */
    @SuppressWarnings("rawtypes")
    public static LoginAppUser getLoginAppUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof OAuth2Authentication) {
            OAuth2Authentication oAuth2Auth = (OAuth2Authentication) authentication;
            authentication = oAuth2Auth.getUserAuthentication();

            if (authentication instanceof UsernamePasswordAuthenticationToken) {
                UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) authentication;
                Object principal = authentication.getPrincipal();
                if (principal instanceof LoginAppUser) {
                    return (LoginAppUser) principal;
                }

                Map map = (Map) authenticationToken.getDetails();
                map = (Map) map.get("principal");

                return JSONObject.parseObject(JSONObject.toJSONString(map), LoginAppUser.class);
            }
        }

        return null;
    }

    /**
     * 校验oss登录用户名
     */
    private static String USERNAME_REGEX = "^[A-Za-z][A-Za-z0-9-]+$";
    private static Pattern P_USERNAME = Pattern.compile(USERNAME_REGEX);
    public static boolean checkUsername(String username){
        if (StringUtils.isBlank(username)) {
            return Boolean.FALSE;
        }

        Matcher m = P_USERNAME.matcher(username);
        return m.matches();

    }
}
