package com.spring.tryAngle.Util;

import java.security.MessageDigest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author 김의현
 * @category 싱글턴 패턴
 * @since 2017.09.22 
 *
 */

public class Construct {
	
	/* 싱글턴 패턴 */
	private static Construct intence = new Construct();
	
	public static Construct getIntence() {
		
		return intence;
		
	};
	

}
