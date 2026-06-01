package in.ms.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Mycontroller {
	
	@GetMapping("/")
	public String home(){
		
		return "index";
		
	}
	
	@Autowired
	private JavaMailSender javamailsender;
	
	@PostMapping("/contact/send")
	public String sendmail(@RequestParam("clientName") String name,
            @RequestParam("clientEmail") String email,
            @RequestParam("clientMessage") String message) {
		try {
			
			SimpleMailMessage emailpayload = new SimpleMailMessage();
			emailpayload.setTo("mangesh3918@gmail.com");
			emailpayload.setSubject("Portfolio Contact Form: Connection from " + name);
			String structuredBody = "You received a new message from your portfolio site:\n\n" +
                    "Sender Name: " + name + "\n" +
                    "Sender Email: " + email + "\n\n" +
                    "Message:\n" + message;
			emailpayload.setText(structuredBody);
			javamailsender.send(emailpayload);
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/#contact?erro";
			// TODO: handle exception
		}
		return "redirect:/#contact?success";
	}

}
