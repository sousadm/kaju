package br.com.kaju.repository;

import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.kaju.model.Categoria;
import br.com.kaju.util.jpa.Transactional;

public class Categorias implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Categoria porId(Long id) {
		Categoria cat = manager.find(Categoria.class, id);
		return cat;
	}

	public List<Categoria> lista() {
		return manager.createNamedQuery("Categoria.lista", Categoria.class).getResultList();
	}

	@Transactional
	public Categoria guardar(Categoria produto) {
		return manager.merge(produto);
	}

	@Transactional
	public void remover(Categoria produto) {
		produto = porId(produto.getId());
		manager.remove(produto);
		manager.flush();
	}
}