package com.spring.tryAngle.Util;

import java.util.Properties;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * 
 * 
 * @author kim
 * @출처 http://nowonbun.tistory.com/271
 * @출처 http://gangzzang.tistory.com/entry/Java-%EC%98%88%EC%99%B8%EC%B2%98%EB%A6%ACexception-handling
 */
public class GmailSMTP {
	
	private String title;
	private String context;
	
	private String sendId;
	private String adminId;
	
	public GmailSMTP() {};
	
	public GmailSMTP(String title, String context,String sendId) {
		this.title = title;
		this.context = context;
		this.sendId = sendId;
	}

	public boolean sendGmail() throws Exception {
		
		boolean sw = false;
		
		
		try {
			adminId= "송신자 메일";
			
			
	        Properties props = new Properties();
	        props.setProperty("mail.transport.protocol", "smtp");
	        props.setProperty("mail.host", "smtp.gmail.com");
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.port", "465");  // SSL 사용 
	        props.put("mail.smtp.socketFactory.port", "465");
	        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	        props.put("mail.smtp.socketFactory.fallback", "false");
	        props.setProperty("mail.smtp.quitwait", "false");
	         
	        Authenticator auth = new Authenticator(){
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(adminId, adminPw);
	            }
	        };
	    
	        Session session = Session.getDefaultInstance(props,auth);
	         
	        MimeMessage message = new MimeMessage(session);
	        message.setSender(new InternetAddress(adminId));
	        message.setSubject(title);
	 
	        message.setRecipient(Message.RecipientType.TO, new InternetAddress(sendId));
	         
	        Multipart mp = new MimeMultipart();
	        MimeBodyPart mbp1 = new MimeBodyPart();
	        
	        mbp1.setText(context);
	        
	        mp.addBodyPart(mbp1);

	        MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
	        mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
	        mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
	        mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
	        mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
	        mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
	        CommandMap.setDefaultCommandMap(mc);
	        
	        message.setContent(mp);	        

	        Transport.send(message);
			
		}catch(Exception e) {
			System.out.println("애러 발생 :"+e.getMessage());
			System.out.println("애러 발생 :"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		
		return sw;
	}
}
