package com.windaka.suizhi.zjj.controller;

import com.alibaba.fastjson.JSONObject;
import com.windaka.suizhi.zjj.constants.CredentialType;
import com.windaka.suizhi.zjj.constants.ReturnConstants;
import com.windaka.suizhi.zjj.oauth.OssClientInfo;
import com.windaka.suizhi.zjj.service.AppUserService;
import com.windaka.suizhi.zjj.utils.Tools;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.util.OAuth2Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;


/**
 * 登陆、刷新token、退出
 * @author hjt
 */
@Slf4j
@RestController
public class TokenController extends BaseController {

    @Autowired
    private AppUserService userService;

    @Autowired
    private RestTemplate restTemplate;
/*
    @Autowired
    private RedisTokenStore redisTokenStore;*/


    /**
     * 系统登陆<br>
     * 根据用户名登录<br>
     * 采用oauth2密码模式获取access_token和refresh_token
     * @return
     * @see org.springframework.security.oauth2.provider.endpoint.TokenEndpoint
     */
    @PostMapping("/plat/login")
    public Map<String,Object> login(@RequestBody Map<String, Object> params) {
        System.out.println("登录集群测试1111111");
        if(params == null){
            return super.failRender(ReturnConstants.CODE_FAILED,
                    ReturnConstants.MSG_LOGIN_USERNAME_IS_NULL);
        }
        String username = Tools.null2String((String)params.get("username"));
        String password = Tools.null2String((String)params.get("password"));
      //  String verifyCode = Tools.null2String((String)params.get("verifyCode"));
        try {


            if(StringUtils.isBlank(username)){
                return super.failRender(ReturnConstants.CODE_FAILED,
                        ReturnConstants.MSG_LOGIN_USERNAME_IS_NULL);
            }

            if(StringUtils.isBlank(password)){
                return super.failRender(ReturnConstants.CODE_FAILED,
                        ReturnConstants.MSG_LOGIN_PASSWORD_IS_NULL);
            }

        /*    if(StringUtils.isBlank(verifyCode)){
                return super.failRender(ReturnConstants.CODE_FAILED,
                        ReturnConstants.MSG_VERIFY_CODE_IS_NULL);
            }*/

            //首先验证验证码是否正确
   /*         if(!zuulKaptcha.validate(verifyCode, 300)){
                return super.failRender(ReturnConstants.CODE_FAILED,
                        ReturnConstants.MSG_LOGIN_FAILED_VERIFYCODE_BAD);
            }*/

            //组装登录参数，准备进入认证中心
            //认证中心通过spring security进行认证，用户信息存入redis及返回token
            Map<String, String> parameters = new HashMap<>();
            parameters.put(OAuth2Utils.GRANT_TYPE, "password");
            parameters.put(OAuth2Utils.CLIENT_ID, OssClientInfo.CLIENT_ID);
            parameters.put("client_secret", OssClientInfo.CLIENT_SECRET);
            parameters.put(OAuth2Utils.SCOPE, OssClientInfo.CLIENT_SCOPE);
            //		parameters.put("username", username);
            // 为了支持多类型登录，这里在username后拼装上登录类型
            parameters.put("username", username + "|" + password + "|" + CredentialType.USERNAME.name());
            parameters.put("password", password);


            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            MultiValueMap<String, String> params1= new LinkedMultiValueMap<>();
            params1.add("grant_type","password");
            params1.add(OAuth2Utils.SCOPE,OssClientInfo.CLIENT_SCOPE);
            params1.add("client_id",OssClientInfo.CLIENT_SECRET);
            params1.add("client_secret",OssClientInfo.CLIENT_SECRET);
            params1.add("username", username + "|" + password + "|" + CredentialType.USERNAME.name());
            params1.add("password", password);
           // params.add("redirect_uri","http://localhost:8081/aiqiyi/qq/redirect");
            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params1, headers);
            ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8444/oauth/token", requestEntity, String.class);
            String token = response.getBody();
            JSONObject contentJson = JSONObject.parseObject(token);
            Map<String,Object> tokenInfo=(Map<String,Object>) contentJson;
            tokenInfo.get("access_token");

            Map<String, Object> data = null;
            if (tokenInfo != null && !StringUtils.isBlank(tokenInfo.get("access_token").toString())) {
                saveLoginLog(username,"用户名密码登陆", true);
                data = new HashMap<>();
                data.put("access_token", tokenInfo.get("access_token"));
                data.put("refresh_token", tokenInfo.get("refresh_token"));
                data.put("userId", ((Map<String, Object>)tokenInfo.get("loginUser")).get("userId"));

                return super.render(data, ReturnConstants.CODE_LOGIN_SUCESS, ReturnConstants.MSG_LOGIN_SUCESS);
            } else {
                saveLoginLog(username,"用户名密码登陆", false);
                return super.failRender(ReturnConstants.CODE_LOGIN_FAILEED_USER_OR_PWD_BAD, ReturnConstants.MSG_LOGIN_FAILEED_USER_OR_PWD_BAD);
            }
        }catch (Exception e){
            log.error("[TokenController.login]->用户{}登录失败,失败信息：{}", username,  e.getMessage());

            return super.failRender(ReturnConstants.CODE_FAILED, ReturnConstants.MSG_LOGIN_FAILED);
        }
    }



    /**
     * 系统刷新refresh_token
     *
     * @param refresh_token
     * @return
     */
    @PostMapping("/oss/refresh_token")
    public Map<String, Object> refresh_token(String refresh_token) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put(OAuth2Utils.CLIENT_ID, OssClientInfo.CLIENT_ID);
        parameters.put("client_secret", OssClientInfo.CLIENT_SECRET);
        parameters.put(OAuth2Utils.SCOPE, OssClientInfo.CLIENT_SCOPE);
        parameters.put("refresh_token", refresh_token);
        parameters.put(OAuth2Utils.GRANT_TYPE, "refresh_token");
        return restTemplate.postForObject("localhost:8444/oauth/token",parameters,Map.class);
    }

    /**
     * 退出
     */
    @GetMapping("/plat/logout")
    public void logout(String access_token, @RequestHeader(required = false, value = "Authorization") String token) {
        if (StringUtils.isBlank(access_token)) {
            if (StringUtils.isNotBlank(token)){
                access_token = token.substring(OAuth2AccessToken.BEARER_TYPE.length() + 1);
            }
        }
     //   redisTokenStore.readAccessToken(access_token);
    }

    /**
     * 登陆日志
     *
     * @param username
     */
    private void saveLoginLog(String username, String remark, boolean flag) {
        //   log.info("{}登陆,凭证{}", remark, username);
        //异步手动调用日志消息发送客户端，发送消息队列
        //    logMqClient.sendLogMsg(null,username,"登录",null, remark, flag, Tools.getIpAddress(request));
    }



}
