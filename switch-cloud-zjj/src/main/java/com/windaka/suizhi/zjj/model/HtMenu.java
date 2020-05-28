package com.windaka.suizhi.zjj.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * OSS端menu实体
 * @author: hjt
 * @Date: 2018/12/10 10:28
 * @Version 1.0
 */
@Data
public class HtMenu implements Serializable {
    private static final long serialVersionUID = -5976401413983353455L;

    private String menuId;		//菜单id
    private String pmenuId;		//父菜单id
    private String menuName; 	//菜单名称
    private String icon;        //功能图标
    private Integer isDir;		//是否是目录
    private String url;			//连接地址
    private Integer status;		//目录状态
    private Integer orderNum;	//排序号
    private Date creTime;		//创建时间
    private Date updTime;		//修改时间

    private List<HtMenu> child;
}
