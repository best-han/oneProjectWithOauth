package com.windaka.suizhi.zjj.controller;

import com.windaka.suizhi.zjj.constants.ReturnConstants;
import com.windaka.suizhi.zjj.exception.OssRenderException;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 基础Controller
 * 说明：所有controller都继承此类
 * @author: hjt
 */
@Getter
@Setter
public abstract class BaseController {
    /**
     * 所有增删改最终成功后统一通过此方法返回
     * @return
     */
    public Map<String,Object> render(){
        Map<String,Object> data = new HashMap<>();
        data.put("status", ReturnConstants.STATUS_SUCCESS);
        data.put("msg", ReturnConstants.MSG_OPER_SUCCESS);
        data.put("code", ReturnConstants.CODE_SUCESS);

        //返回请求响应
        return data;
    }


    /**
     * 所有请求最终成功后统一通过此方法返回
     * @param object
     * @return
     */
    public Map<String,Object> render(Object object){
        Map<String,Object> data = new HashMap<>();
        data.put("status", ReturnConstants.STATUS_SUCCESS);
        data.put("msg", ReturnConstants.MSG_QUERY_SUCCESS);
        data.put("code", ReturnConstants.CODE_SUCESS);

        //查询请求object不为null
        if(null != object){
            data.put("data", object);
        }
        //返回请求响应
        return data;
    }

    /**
     * 所有请求最终成功后统一通过此方法返回
     * @param object
     * @return
     */
    public Map<String,Object> render(Object object,String code, String msg){
        Map<String,Object> data = new HashMap<>();
        data.put("status", ReturnConstants.STATUS_SUCCESS);
        data.put("msg", msg);
        data.put("code", code);

        //查询请求object不为null
        if(null != object){
            data.put("data", object);
        }
        //返回请求响应
        return data;
    }

    /**
     * 失败返回
     * @param code
     * @param msg
     * @return
     */
    public Map<String,Object> failRender(String code, String msg){
        Map<String,Object> data = new HashMap<>();
        data.put("status", ReturnConstants.STATUS_FAILED);
        data.put("msg", msg);
        data.put("code", code);
        return data;
    }

    /**
     * 失败返回
     * @param exception
     * @return
     */
    public Map<String,Object> failRender(Exception exception){
        String msg = exception.getMessage();
        String code = null;

        if (exception instanceof OssRenderException){
            OssRenderException ore = (OssRenderException) exception;
            msg = ore.getMsg();
            code = ore.getCode();
        }else {
            if (!StringUtils.isBlank(msg) && msg.indexOf("|^|^|") >= 0 && msg.indexOf("|^|^|") < 100) {
                code = msg.substring(0, msg.indexOf("|^|^|"));
                msg = msg.substring(msg.indexOf("|^|^|") + 1);
            } else {
                code = ReturnConstants.CODE_FAILED;
                msg = ReturnConstants.MSG_FAILED;
            }
        }
        return failRender(code, msg);
    }


}
