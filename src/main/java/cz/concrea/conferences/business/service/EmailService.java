package cz.concrea.conferences.business.service;

import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import cz.concrea.conferences.business.dao.entity.Conference;
import cz.concrea.conferences.business.dao.entity.Invoice;
import cz.concrea.conferences.business.dao.entity.UserRegistration;
import cz.concrea.conferences.business.model.form.AIOForm;

@Service
public class EmailService {

	@Autowired
	private JavaMailSenderImpl javaMailSender;

	@Autowired
	private TemplateEngine templateEngine;
	
	@Autowired
	CryptLongParameterService cryptService;

	public void SendAIOCompleted(final AIOForm aioform, final Conference conference, final Invoice invoice,
			UserRegistration regInfo, final Locale locale) {
		final Context ctx = new Context(locale);
		ctx.setVariable("userRegistration", regInfo);
		ctx.setVariable("aioform", aioform);
		ctx.setVariable("invoice", invoice);
		try {
			ctx.setVariable("invoicelink", "http://sys.concrea.cz/confs/"+conference.getCodeName()+"/invoice.pdf?inv=" + cryptService.encryptLong(invoice.getId()));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		ctx.setVariable("conference", conference);
		final String htmlContent = this.templateEngine.process("AIOForm/aiomail", ctx);

		MimeMessage mail = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mail, false, "UTF-8");
			helper.setTo(aioform.getPersonalDataForm().getEmail());
			helper.setReplyTo("info@concrea.cz");
			helper.setFrom("info@concrea.cz");
			helper.setSubject("Potvrzení - Registrace " + conference.getName());
			mail.setContent(htmlContent, "text/html; charset=UTF-8");
		} catch (MessagingException e) {
			e.printStackTrace();
		} finally {
		}
		javaMailSender.send(mail);
	}

}
