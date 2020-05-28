package com.windaka.suizhi.zjj.config;

import com.windaka.suizhi.zjj.LogImpl.LoginInfoImpl;
import com.windaka.suizhi.zjj.log.LoginInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: hjt
 * @Date: 2018/12/21 01:59
 * @Version 1.0
 */
@Configuration
public class LogConfig {

    @Bean
    public LoginInfo loginInfo(){
        return new LoginInfoImpl();
    }

}
