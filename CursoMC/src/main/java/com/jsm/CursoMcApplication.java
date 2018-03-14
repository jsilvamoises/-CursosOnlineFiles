package com.jsm;

import java.math.BigDecimal;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jsm.domain.Categoria;
import com.jsm.domain.Cidade;
import com.jsm.domain.Cliente;
import com.jsm.domain.Endereco;
import com.jsm.domain.Estado;
import com.jsm.domain.Produto;
import com.jsm.domain.enums.TipoCliente;
import com.jsm.repositories.ClienteRepository;
import com.jsm.repositories.EnderecoRepository;
import com.jsm.repositories.EstadoRepository;
import com.jsm.repositories.ProdutoRepository;
import com.jsm.services.CategoriaService;

@SpringBootApplication
public class CursoMcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaService catService;
	
	@Autowired
	private ProdutoRepository prodService;
	
	@Autowired
	private EstadoRepository estadoRep;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository repEndereco;
	
	
	public static void main(String[] args) {
		SpringApplication.run(CursoMcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria c1 = new Categoria(null,"T");
		Categoria c2 = new Categoria(null,"P");
		Categoria c3 = new Categoria(null,"Z");
		
		Produto p1 = new Produto();
		p1.setNome("Produto um");
		p1.setPreco(BigDecimal.TEN);
		
		Produto p2 = new Produto();
		p2.setNome("Produto Dois");
		p2.setPreco(BigDecimal.TEN);
		
		Produto p3 = new Produto();
		p3.setNome("Produto Tres");
		p3.setPreco(BigDecimal.TEN);
		
		
	
	    p1.getCategorias().addAll(Arrays.asList(c1,c2));
	    p3.getCategorias().addAll(Arrays.asList(c3));
	   
		Arrays.asList(p1,p2,p3).iterator().forEachRemaining(p-> prodService.save(p) );
	
		Estado mg = new Estado("Minas Gerais");
		Estado sp = new Estado("São Paulo");
		
		Cidade uberlandia  = new Cidade("Uberlandia",mg);
		Cidade saoPaulo = new Cidade("São Paulo",sp);
		Cidade campinas = new Cidade("Campinas",sp);
		sp.getCidades().addAll(Arrays.asList(uberlandia));
		
		sp.getCidades().addAll(Arrays.asList(saoPaulo,campinas));
		
		estadoRep.saveAll(Arrays.asList(mg,sp));
		
		Cliente mariaSilva = new Cliente(null, "maria@gmail.com", "Maria Silva", "365.256.258-00", TipoCliente.PESSOA_FISICA);
		mariaSilva.getTelefones().addAll(Arrays.asList("(11) 9.5898-9685","(11) 5869-9857"));
		Endereco residencial = new Endereco(null, "Rua Samanbaia", "800", "APT 22", "São Tiago", "56.695-695", saoPaulo, mariaSilva);
		Endereco comercial = new Endereco(null, "Rua Guaiba", "600", "Proximo ao mercado tor", "São Benedito", "36.652-987", campinas, mariaSilva);
		
		residencial.setCliente(mariaSilva);
		comercial.setCliente(mariaSilva);
		clienteRepository.save(mariaSilva);
		
		repEndereco.save(residencial);
		repEndereco.save(comercial);
		
		
	}
	
	 
}
