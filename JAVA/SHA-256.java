package com.spring.tryAngle.Util;

import java.security.MessageDigest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author 김의현
 * @category 패스워드 암호화
 * @since 2017.09.22 
 *
 */

public class Construct {	
	
	/* @name 패스워드 암호화 (SHA-256) */
	public String getPassword(String pw) {
		String Pw="";
		try {
			
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(pw.getBytes());
			byte byteData[] = md.digest();

			StringBuffer sb = new StringBuffer(); // 스트링버퍼가 스프링보다 문자를 붙일때 더 빠름			
			
			for(int i=0; i<byteData.length; i++) {
				
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));		
				
			}
			
			pw=sb.toString();
			 
		}catch(Exception e) {
			
			pw="PwError";
			
		}
		
		return pw;
	}
	

}
