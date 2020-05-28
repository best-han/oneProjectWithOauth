package com.windaka.suizhi.zjj.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * 自定义用户中心异常
 * @author: hjt
 */
@Getter
@Setter
public class OssException extends Exception {

    private static final long serialVersionUID = 7609049992975015623L;
    private String msg;
    private String code;

    public OssException(){super();}

    public OssException(String msg){
        super(msg);
        this.msg=msg;
    }

    public OssException(String code, String msg){
        super(msg);
        this.code = code;
        this.msg=msg;
    }

    public OssException(Throwable t){super(t);}

    public OssException(String msg, Throwable t){super(msg, t);}
}
