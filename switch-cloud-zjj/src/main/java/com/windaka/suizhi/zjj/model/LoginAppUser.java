package com.windaka.suizhi.zjj.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * spring security当前登录对象
 */
@Getter
@Setter
public class LoginAppUser extends AppUser implements UserDetails {

	private static final long serialVersionUID = 1753977564987556640L;

//	private Area area;		//用户的区域信息
//	private WyAndXqInfo wyAndXqInfo; //用户的物业和小区信息
	private Boolean sysAdmin; //用户是否为超级管理员
	private String sysLevel;	//用户级别

	private Set<Role> roles;	//用户的角色信息

	private Set<HtPermission> permissions;	//用户的权限信息

	//private Map<String,Set<HtPermission>> menuPermissionMap;

	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collection = new HashSet<>();
		if (!CollectionUtils.isEmpty(roles)) {
			roles.forEach(role -> {
				collection.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleId()));
			});
		}

		if (!CollectionUtils.isEmpty(permissions)) {
			permissions.forEach(per -> {
				collection.add(new SimpleGrantedAuthority(per.getPermission()));
			});
		}

		return collection;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
