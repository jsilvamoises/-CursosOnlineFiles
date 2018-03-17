package com.jsm.services;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jsm.domain.ItemPedido;
import com.jsm.domain.Pagamento;
import com.jsm.domain.PagamentoComBoleto;
import com.jsm.domain.Pedido;
import com.jsm.domain.enums.EstadoPagamento;
import com.jsm.email.interfaces.EmailService;
import com.jsm.repositories.ClienteRepository;
import com.jsm.repositories.ItemPedidoRepository;
import com.jsm.repositories.PagamentoRepository;
import com.jsm.repositories.PedidoRepository;
import com.jsm.repositories.ProdutoRepository;
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
	private EmailService emailService;

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

	public Pedido post(Pedido obj) {
		obj.setId(null);
		
		
		
		obj.setInstante(new Date());
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if(obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pgto = (PagamentoComBoleto)obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pgto,obj.getInstante());
			
		}
				
		obj = rep.save(obj);
		
		Pagamento pg = pgtoRep.save(obj.getPagamento());
		obj.setPagamento(pg);
		
		for(ItemPedido ip:obj.getItens()) {
			ip.setDesconto(BigDecimal.ZERO);
			ip.setPreco(prodRep.getOne(ip.getProduto().getId()).getPreco());
			ip.setPedido(obj);
		}
		
		ipRep.saveAll(obj.getItens());
		
		
		obj = rep.getOne(obj.getId());
		
		//System.out.println(entity);
		
		emailService.sendOrderConfirmationEmail(obj);
		
		return obj;
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
}
