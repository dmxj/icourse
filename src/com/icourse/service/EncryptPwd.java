package com.icourse.service;

import com.icourse.util.HashTool;

public class EncryptPwd {

	public static String encryptPwd(String pwd){
		String a = HashTool.MD5(pwd);
		String b = HashTool.MD5("icourse_of_cuc");
		String s = HashTool.SHA1(a.concat(b));
		return s;
	}
	
}
