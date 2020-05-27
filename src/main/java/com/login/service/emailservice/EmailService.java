package com.login.service.emailservice;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import java.io.IOException;

public class EmailService {
	public static void sendEmailVerifyAccount(String email_to, String url) throws IOException {
		/*String url_verify_account*/
		Email from = new Email("micrinapp@gmail.com");
		String subject = "Verificar cuenta Micrin";
		Email to = new Email(email_to);
		Content content = new Content("text/html", "Verificar cuenta Micrin");
		Mail mail = new Mail(from, subject, to, content);
		mail.personalization.get(0).addSubstitution("-url-", url);
		mail.setTemplateId("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		
		/*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*/
		SendGrid sg = new SendGrid("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		Request request = new Request();
		try {
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());
			Response response = sg.api(request);
			System.out.println(response.getStatusCode());
			System.out.println(response.getBody());
			System.out.println(response.getHeaders());
		} catch (IOException ex) {
			throw ex;
		}
	}
	
	public static void changePassword(String email_to, String url_change_password) throws IOException {

		String userName = email_to.replace(" ", "");
		userName = removeCharacters(userName);
		String[] userNameSplit = userName.split(" ");
		String name = "";
		for(int i=0; i<1; i++) {
			name += " "+ucFirst(userNameSplit[0]);
		}

		Email from = new Email(" ");
		String subject = " ";
		Email to = new Email(email_to);
		Content content = new Content("text/html", "Verify you email");
		Mail mail = new Mail(from, subject, to, content);
		mail.personalization.get(0).addSubstitution("-name-", name);
		mail.personalization.get(0).addSubstitution("-url_change_password-", url_change_password);
		mail.setTemplateId("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");

		SendGrid sg = new SendGrid("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		Request request = new Request();
		try {
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());
			Response response = sg.api(request);
			System.out.println(response.getStatusCode());
			System.out.println(response.getBody());
			System.out.println(response.getHeaders());
		} catch (IOException ex) {
			throw ex;
		}
	}

	public static String removeCharacters(String userName) {
		userName = userName.replace("-", " "); 
		userName = userName.replace(".", " ");
		userName = userName.replace("_", " ");
		return userName;
	}

	public static String ucFirst(String str) {
		if (str == null || str.isEmpty()) {
			return str;            
		} else {
			return str.substring(0, 1).toUpperCase() + str.substring(1); 
		}
	}

}
