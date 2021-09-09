package br.com.kaju.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.kaju.model.CondicaoFilter;
import br.com.kaju.model.Produto;
import br.com.kaju.util.jpa.Transactional;

public class Produtos implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Produto porId(Long id) {
		return manager.find(Produto.class, id);
	}

	public List<Produto> lista(CondicaoFilter filtro) {
		String condicao = "select p from Produto p where 1 = 1 ";
		if (filtro.getNome() != null) {
			condicao += " and UPPER(p.descricao) like '%" + filtro.getNome().toUpperCase() + "%'";
		}
		condicao += " order by p.descricao";
		return manager.createQuery(condicao, Produto.class).getResultList();
	}

	@Transactional
	public Produto guardar(Produto produto) {
		return manager.merge(produto);
	}

	@Transactional
	public void remover(Produto produto) {
		produto = porId(produto.getId());
		manager.remove(produto);
		manager.flush();
	}

}
