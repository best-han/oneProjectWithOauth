package com.windaka.suizhi.zjj.exception;

/**
 * 自定义用户中心请求处理失败-返回异常
 * @author: hjt
 */
public class OssRenderException extends OssException {
    private static final long serialVersionUID = 2615258270841185220L;

    public OssRenderException(){super();}

    public OssRenderException(String msg){super(msg);}

    public OssRenderException(String code, String msg){
        super(code, msg);
    }

    public OssRenderException(Throwable t){super(t);}

    public OssRenderException(String msg, Throwable t){super(msg, t);}
}
