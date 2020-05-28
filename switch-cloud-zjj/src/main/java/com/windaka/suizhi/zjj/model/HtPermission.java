package com.windaka.suizhi.zjj.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 权限标识
 * @author hjt
 */
@Data
public class HtPermission implements Serializable {

	private static final long serialVersionUID = 280565233032255804L;

	private String permissionId;		//操作按钮id
	private String menuId;				//所属菜单id
	private String permission;			//操作标识
	private String permissionName;		//按钮名称
	private String creBy;				//创建人
	private Date creTime;				//创建时间
	private String updBy;				//修改人
	private Date updTime;				//修改时间

}
