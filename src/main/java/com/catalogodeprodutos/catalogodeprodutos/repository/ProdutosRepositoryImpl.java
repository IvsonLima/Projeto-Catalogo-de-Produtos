package com.catalogodeprodutos.catalogodeprodutos.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.catalogodeprodutos.catalogodeprodutos.model.Produtos;

public class ProdutosRepositoryImpl implements ProdutosRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Produtos> filtrar(String q, String minPrice, String maxPrice) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Produtos> criteria = builder.createQuery(Produtos.class);
		Root<Produtos> root = criteria.from(Produtos.class);

		Predicate[] predicates = criarRestricoes(q, minPrice, maxPrice, builder, root);
		criteria.where(predicates);

		TypedQuery<Produtos> query = manager.createQuery(criteria);

		return query.getResultList();
	}

	private Predicate[] criarRestricoes (String q, String maxPrice, String minPrice, CriteriaBuilder builder, Root<Produtos> root) {
		List <Predicate> predicates = new ArrayList<>();
		if(!q.isEmpty()) {
			//predicates.add(builder.like(builder.lower(root.get("description")), "%" + q.toLowerCase() + "%"));
			predicates.add(builder.like(builder.lower(root.get("name")), "%" + q.toLowerCase() + "%"));
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}
}
