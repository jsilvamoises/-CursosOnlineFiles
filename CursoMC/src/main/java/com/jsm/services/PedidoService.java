package com.jsm.services;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jsm.domain.Cliente;
import com.jsm.domain.Endereco;
import com.jsm.domain.ItemPedido;
import com.jsm.domain.Pagamento;
import com.jsm.domain.PagamentoComBoleto;
import com.jsm.domain.Pedido;
import com.jsm.domain.Produto;
import com.jsm.domain.enums.EstadoPagamento;
import com.jsm.email.EmailService;
import com.jsm.repositories.ClienteRepository;
import com.jsm.repositories.EnderecoRepository;
import com.jsm.repositories.ItemPedidoRepository;
import com.jsm.repositories.PagamentoRepository;
import com.jsm.repositories.PedidoRepository;
import com.jsm.repositories.ProdutoRepository;
import com.jsm.security.UserSS;
import com.jsm.security.service.UserService;
import com.jsm.security.service.exception.AuthorizationException;
import com.jsm.services.exception.ObjectNotFoundException;

@Service
public class PedidoService {
	@Autowired
	PedidoRepository rep;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pgtoRep;
	
	@Autowired
	private ProdutoRepository prodRep;
	
	@Autowired
	private ItemPedidoRepository ipRep;
	
	@Autowired
	private ClienteRepository cliRep;
	
	@Autowired
	private EnderecoRepository endRep;
	
	
	
	@Autowired
	@Qualifier("pedidoEmailService")
	private EmailService<Pedido> emailService;

	public Pedido get(Long id) {
		Optional<Pedido>  cat = rep.findById(id);
		if(!cat.isPresent()) {
			throw new ObjectNotFoundException("Objeto não encontrado! "
					+ "ID: "+id+" | Tipo: "+ Pedido.class.getName());
			
		}
		return cat.get();
	}

	public List<Pedido> get() {
		return rep.findAll();
	}

	public Page<Pedido> get(Pageable pageable) {
		return rep.findAll(pageable);
	}

	public Pedido post(Pedido pedido) {
		pedido.setId(null);
		
		Cliente c = cliRep.getOne(pedido.getCliente().getId());
		pedido.setCliente(c);
		
		if(pedido.getEnderecoEntrega()!=null && pedido.getEnderecoEntrega().getId()!=null) {
			Endereco e = endRep.getOne(pedido.getEnderecoEntrega().getId());
			pedido.setEnderecoEntrega(e);
		}
	
		pedido.setInstante(new Date());
		pedido.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		pedido.getPagamento().setPedido(pedido);
		if(pedido.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pgto = (PagamentoComBoleto)pedido.getPagamento();
			boletoService.preencherPagamentoComBoleto(pgto,pedido.getInstante());
			
		}
				
		pedido = rep.save(pedido);
		
		Pagamento pg = pgtoRep.save(pedido.getPagamento());
		pedido.setPagamento(pg);
		
		for(ItemPedido ip:pedido.getItens()) {
			Produto p = prodRep.getOne(ip.getId().getProduto().getId());
			ip.setDesconto(BigDecimal.ZERO);
			ip.setPreco(p.getPreco());
			ip.setPedido(pedido);
			ip.setProduto(p);
		}
		
		ipRep.saveAll(pedido.getItens());
		
		
		pedido = rep.getOne(pedido.getId());
		
		System.out.println(pedido);
		
		
		
		//emailService.sendOrderConfirmationEmail(pedido.getId());
		emailService.sendHtmlEmail(pedido);
		return pedido;
	}

	public Pedido put(Long id, Pedido entity) {
		Pedido savedEntity = get(id);
		BeanUtils.copyProperties(entity, savedEntity, "id");
		entity = rep.save(savedEntity);
		return entity;
	}
	
	public void delete(Long id) {

		get(id);
		rep.deleteById(id);
	}
	
	public Page<Pedido> getByCliente(Pageable pageable){
		UserSS user = UserService.autheticated();
		
		if(user == null) {
			throw new AuthorizationException("Acesso Negado!");
		}
		Cliente cli = cliRep.getOne(user.getId());
		
		return rep.findByCliente(cli, pageable);
		
	}
}
