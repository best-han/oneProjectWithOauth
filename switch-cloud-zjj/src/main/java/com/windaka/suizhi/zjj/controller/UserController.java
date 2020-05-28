package com.windaka.suizhi.zjj.controller;

import cn.hutool.core.map.MapUtil;
import com.windaka.suizhi.zjj.constants.ReturnConstants;
import com.windaka.suizhi.zjj.model.LoginAppUser;
import com.windaka.suizhi.zjj.service.AppUserService;
import com.windaka.suizhi.zjj.utils.AppUserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 用户管理
 * @author hjt
 * @version 1.0
 */
@Slf4j
@RestController
public class UserController extends BaseController {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private HttpServletRequest request;
    /**
     * 当前登录用户 hjt
     * @return LoginAppUser
     */
    @GetMapping("/users/current")
    public Map<String,Object> getLoginAppUser() throws Exception{
        try {
            Map<String, Object> map = null;
            LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
            if (loginAppUser == null) {
                throw new Exception(ReturnConstants.STATUS_TOKEN_FAILED);
            } else {
                loginAppUser.setPassword("");
            }
            return render(loginAppUser);
        } catch (Exception e){
            log.info("[UserController.getLoginAppUser,异常信息{}]",e.getMessage());
            return failRender(e);
        }
    }

    /**
     * 获取用户基本信息 hjt
     * @return
     * @throws Exception
     */
    @GetMapping("/me")
    public Map<String,Object> getLoginAppUserBasicInfo() throws Exception{
        try {
            Map<String, Object> map = null;
            LoginAppUser loginAppUser = AppUserUtil.getLoginAppUser();
            map=appUserService.queryByUserId(loginAppUser.getUserId());
            if (MapUtil.isEmpty(map)) {
                throw new Exception(ReturnConstants.STATUS_TOKEN_FAILED);
            }
            return render(map);
        } catch (Exception e){
            log.info("[UserController.getLoginAppUser,异常信息{}]",e.getMessage());
            return failRender(e);
        }
    }

    /**
     * 通过username查询用户信息
     * @param username
     * @return
     */
    @GetMapping(value = "/users-anon/internal", params = {"username","password"})
    public Map<String,Object> queryByUsername(String username, String password){

        try {
            //查询登录用户
            LoginAppUser loginAppUser = appUserService.queryByUsername(username, password);//用于获取token前查看是否有此用户
            if(loginAppUser != null){
                return render(loginAppUser);
            }else{
                return failRender(ReturnConstants.CODE_LOGIN_FAILEED_USER_OR_PWD_BAD, ReturnConstants.MSG_LOGIN_FAILEED_USER_OR_PWD_BAD);
            }
        }catch (Exception e){
            log.info("[UserController.queryByUsername,参数：{},{},异常信息{}]",username,password,e.getMessage());
            return failRender(e);
        }

    }




}
