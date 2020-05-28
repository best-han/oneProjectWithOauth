package com.windaka.suizhi.zjj.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色
 * @author hjt
 */
@Data
public class Role implements Serializable {

	private static final long serialVersionUID = -2054359538140713354L;

	private String roleId;
	private String roleName;
	private String areaId;
	private String creBy;
	private Date creTime;
	private String updBy;
	private Date updTime;
}
