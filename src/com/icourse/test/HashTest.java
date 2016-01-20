package com.icourse.test;

import com.icourse.service.EncryptPwd;
import com.icourse.util.HashTool;

public class HashTest {

	public static void main(String[] args) {
//		String s = "1c2ec05";
//		System.out.println(HashTool.decryptAES(s.getBytes(),"123"));
		
		System.out.println(EncryptPwd.encryptPwd("admin"));
	}

}
