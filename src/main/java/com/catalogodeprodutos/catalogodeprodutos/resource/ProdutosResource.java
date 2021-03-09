package com.catalogodeprodutos.catalogodeprodutos.resource;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import com.sun.istack.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.catalogodeprodutos.catalogodeprodutos.model.Produtos;
import com.catalogodeprodutos.catalogodeprodutos.repository.ProdutosRepository;
import com.catalogodeprodutos.catalogodeprodutos.service.ProdutosService;

@RestController
@RequestMapping("/products")
public class ProdutosResource {

	@Autowired
	private ProdutosService produtosService;

	@Autowired
	private ProdutosRepository produtosRepository;

	@GetMapping // Lista de produtos
	public List<Produtos> listar() {
		return produtosRepository.findAll();
	}

	// Criação de um produto
	@PostMapping
	public ResponseEntity<Produtos> criar(@RequestBody Produtos produtos, HttpServletResponse response) {
		Produtos produtosSalvo = produtosRepository.save(produtos);
		if (produtos.getPrice().compareTo(BigDecimal.ZERO) != 1 || produtos.getPrice().equals(null)) {
			return ResponseEntity.badRequest().build();
		}
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(produtosSalvo.getId()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		return ResponseEntity.created(uri).body(produtosSalvo);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Produtos> buscarPeloCodigo(@PathVariable Long id) {
		Produtos produtos = produtosRepository.findById(id).orElse(null);
		return produtos != null ? ResponseEntity.ok(produtos) : ResponseEntity.notFound().build();

	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void remover(@PathVariable Long id) {
		produtosRepository.deleteById(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Produtos> atualizar(@PathVariable Long id, @Validated @RequestBody Produtos produtos) {
		Produtos produtoSalvo = produtosService.atualizar(id, produtos);
		if (produtos.getPrice().compareTo(BigDecimal.ZERO) != 1 || produtos.getPrice().equals(null)) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(produtoSalvo);
	}

	@GetMapping("/search")
	public ResponseEntity<List<Produtos>> buscarParametros(@DefaultValue("") @RequestParam String q,
			@DefaultValue("") @RequestParam String minPrice, @DefaultValue("") @RequestParam String maxPrice) {
		List<Produtos> produtos = produtosRepository.filtrar(q, minPrice, maxPrice);
		return ResponseEntity.ok(produtos);
	}

}
