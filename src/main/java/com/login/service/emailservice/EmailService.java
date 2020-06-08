package com.login.service.emailservice;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import java.io.IOException;

public class EmailService {
	public static void sendEmailVerifyAccount(String email_to, String url) throws IOException {
		/*String url_verify_account*/
		Email from = new Email("micrinm@gmail.com");
		String subject = "Verificar cuenta Micrin";
		Email to = new Email(email_to);
		Content content = new Content("text/html", "Verificar cuenta Micrin");
		Mail mail = new Mail(from, subject, to, content);
		mail.personalization.get(0).addSubstitution("-url-", url);
		mail.setTemplateId("fddc5a73-5cfd-4948-b939-336282367b19");
		
		/*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*/
		SendGrid sg = new SendGrid("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");
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
	
	public static void recuperarCuenta(String email_to, String url) throws IOException {
		/*String url_verify_account*/
		Email from = new Email("micrinm@gmail.com");
		String subject = "Recuperar cuenta Micrin";
		Email to = new Email(email_to);
		Content content = new Content("text/html", "Recuperar cuenta Micrin");
		Mail mail = new Mail(from, subject, to, content);
		mail.personalization.get(0).addSubstitution("-url-", url);
		mail.setTemplateId("a8c49554-e5b7-4dad-8460-ff8bd95848ad");
		
		SendGrid sg = new SendGrid("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");
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

		String userName = email_to.replace("@endava.com", "");
		userName = removeCharacters(userName);
		String[] userNameSplit = userName.split(" ");
		String name = "";
		for(int i=0; i<1; i++) {
			name += " "+ucFirst(userNameSplit[0]);
		}

		Email from = new Email("bookup@endava.com");
		String subject = "Bookup account";
		Email to = new Email(email_to);
		Content content = new Content("text/html", "Verify you email");
		Mail mail = new Mail(from, subject, to, content);
		mail.personalization.get(0).addSubstitution("-name-", name);
		mail.personalization.get(0).addSubstitution("-url_change_password-", url_change_password);
		mail.setTemplateId("7ef764eb-83df-4b99-bab2-9ee59d66ee30");

		SendGrid sg = new SendGrid("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");
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
