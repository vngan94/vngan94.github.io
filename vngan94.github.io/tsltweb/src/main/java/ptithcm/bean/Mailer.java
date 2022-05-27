package ptithcm.bean;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service("mailer")
public class Mailer {
	@Autowired
	JavaMailSender mailer;
	public void send(String from, String to, String subject, String body ) {
		try {
			MimeMessage mail = mailer.createMimeMessage(); // tao mail
			MimeMessageHelper helper = new MimeMessageHelper(mail);
			helper.setFrom(from, from); // set mail gui
			helper.setTo(to); // mail nhan
			helper.setSubject(subject); // tieu de
			helper.setText(body, true); // noidung
			helper.setReplyTo(from, from); // cap thong tin de nguoi nhan phan hoi
			mailer.send(mail);		// gui mail
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
