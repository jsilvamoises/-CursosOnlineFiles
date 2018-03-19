package com.jsm.email.smtp;

import java.util.Calendar;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.jsm.domain.Pedido;
import com.jsm.email.service.impl.AbstractEmailServiceImpl;
import com.jsm.email.service.impl.MockEmailServiceImpl;
import com.jsm.repositories.PedidoRepository;

@Service
public class PedidoEmailService extends AbstractEmailServiceImpl<Pedido>  {

	
	private static Logger LOG = LoggerFactory.getLogger(MockEmailServiceImpl.class);

	@Autowired
	private PedidoRepository repository;
	
	@Autowired
	JavaMailSender sender;
	
	
	
	
	public void sendOrderConfirmationEmail(Long pedido) {
		SimpleMailMessage sm = prepareSimpleMailFromPedido(pedido);
		sendEmail(sm);

	}
	
	

	private SimpleMailMessage prepareSimpleMailFromPedido(Long pedidoId) {
		
		

		
		Pedido pedido = repository.getOne(pedidoId);
		
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(pedido.getCliente().getEmail());
		sm.setFrom(getDefaultSender());
		sm.setSubject("Confirmação de Pedido: "+pedido.getId());
		sm.setSentDate(Calendar.getInstance().getTime());
		sm.setText(pedido.toString());
		sm.setBcc("jsilva.moises@gmail.com");
		
		return sm;
	}



	@Override
	public void sendTextPlainEmail(Pedido object,Object...objects) {
		SimpleMailMessage sm = prepareSimpleMailFromPedido(object.getId());
		sendEmail(sm);
		
	}



	@Override
	public void sendHtmlEmail(Pedido object,Object...objects) {
		MimeMessage mm;
		try {
			mm = prepareMimeMessage(object);
			sendHtmlEmail(mm);
		} catch (MessagingException e) {
			sendTextPlainEmail(object);
		}
		
		
	}



	protected MimeMessage prepareMimeMessage(Pedido object) throws MessagingException {
		MimeMessage mm = sender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mm, true);
		mmh.setTo(object.getCliente().getEmail());
		mmh.setFrom(getDefaultSender());
		mmh.setSubject("Pedido Confirmado: "+object.getId());
		mmh.setSentDate(Calendar.getInstance().getTime());
		String htmlText = htmlFromTemplate("email/confirmacaoPedido", "pedido", object);
		mmh.setText(htmlText,true);	
		
		return mm;
	}



	
	
	

}
