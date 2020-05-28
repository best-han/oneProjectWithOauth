package com.windaka.suizhi.zjj.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class AppUser implements Serializable {

	private static final long serialVersionUID = 611197991672067628L;

	private String userId;		//用户ID
	private String username;	//用户名
	private String password;	//密码
	private String cname;		//姓名
	private String phone;		//联系电话
	private String sysLevel;	//用户级别
	private Boolean sysAdmin;  //超级管理员标志
	private Boolean enabled;	//用户状态
	private String delFlag;		//删除标志
	private String creBy;		//创建人
	private Date creTime;		//创建时间
	private String updBy;		//修改人
	private Date updTime;		//修改时间
	private String image;       //头像

}
