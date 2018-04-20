package ftnhps.movieenthusiasts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import ftnhps.movieenthusiasts.users.User;

@Component
public class EmailUtils {

	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	private Environment env;

	@Async
	public void sendEmail(User recipient, String subject, String message) 
			throws MailException, InterruptedException {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(recipient.getEmail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject(subject);
		mail.setText(message);
		javaMailSender.send(mail);

		System.out.println("EMAIL sent: " + subject);
	}

}
