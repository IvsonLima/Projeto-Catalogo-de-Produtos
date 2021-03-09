package com.catalogodeprodutos.catalogodeprodutos.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.catalogodeprodutos.catalogodeprodutos.model.Produtos;
import com.catalogodeprodutos.catalogodeprodutos.repository.ProdutosRepository;

@Service
public class ProdutosService {
	
	@Autowired
	private ProdutosRepository produtoRepository;
	
	public Produtos atualizar(Long id, Produtos produtos){
		Produtos produtoSalvo = buscarProdutoPeloCodigo(id);
		BeanUtils.copyProperties(produtos, produtoSalvo, "id");
		return produtoRepository.save(produtoSalvo);
		
	}
	
	
	public Produtos buscarProdutoPeloCodigo (Long id) {
		Produtos pessoaSalva = this.produtoRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
		return pessoaSalva;
	}
	
	
	
}
