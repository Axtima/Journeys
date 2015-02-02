package com.journeys.util;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.journeys.entity.User;

public class MailService {

	public final static String FROM_EMAIL = "jeremie.ardiot@gmail.com";

	private JavaMailSender mailSender;

	public void sendValidationLink(User user) throws MessagingException {

		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(
				mimeMessage, true);

		mimeMessageHelper.setTo(user.getEmail());
		mimeMessageHelper.setSubject("Validation de votre compte");

		StringBuilder text = new StringBuilder();
		text.append("<html>");
		text.append("<body>");

		text.append("<h2>Bonjour ");
		text.append(user.getFirstName());
		text.append(" ");
		text.append(user.getLastName());
		text.append("</h2>");

		text.append("<h2>Cliquer sur le lien ci-dessous pour valider votre vompte</h2>");
		text.append("<a href=\"");
		text.append("http://localhost:8080/Journeys/app/user/validate/");
		text.append(user.getId());
		text.append("?key=");
		text.append(user.getValidationKey());
		text.append("\">Valider votre compte</a>");

		text.append("</body>");
		text.append("</html>");

		// Sets the text
		mimeMessageHelper.setText(text.toString(), true);

		mailSender.send(mimeMessage);
	}

	public JavaMailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	
}
