package com.jsm;

import java.math.BigDecimal;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jsm.domain.Categoria;
import com.jsm.domain.Produto;
import com.jsm.repositories.ProdutoRepository;
import com.jsm.services.CategoriaService;

@SpringBootApplication
public class CursoMcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaService catService;
	
	@Autowired
	private ProdutoRepository prodService;
	
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
		p2.setNome("Produto um");
		p2.setPreco(BigDecimal.TEN);
		
		Produto p3 = new Produto();
		p3.setNome("Produto um");
		p3.setPreco(BigDecimal.TEN);
		
		
	
	   p1.getCategorias().addAll(Arrays.asList(c1,c2));
	   p3.getCategorias().addAll(Arrays.asList(c3));
	   
		Arrays.asList(p1,p2,p3).iterator().forEachRemaining(p-> prodService.save(p) );
	
		
	}
	
	 
}
