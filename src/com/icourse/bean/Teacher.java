package com.icourse.bean;

public class Teacher {

	private Long uid;
	private String username;
	private String userpwd;
	private String usernum;
	private String LastLoginTime;

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpwd() {
		return userpwd;
	}

	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}

	public String getUsernum() {
		return usernum;
	}

	public void setUsernum(String usernum) {
		this.usernum = usernum;
	}

	public String getLastLoginTime() {
		return LastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		LastLoginTime = lastLoginTime;
	}

}
