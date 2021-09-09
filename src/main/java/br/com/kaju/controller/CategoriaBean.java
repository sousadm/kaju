package br.com.kaju.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import br.com.kaju.model.Categoria;
import br.com.kaju.repository.Categorias;
import br.com.kaju.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CategoriaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Categorias categorias;

	private Categoria categoria;
	private List<Categoria> lista;

	public CategoriaBean() {
		novo();
		lista = new ArrayList<Categoria>();
	}

	public void pesquisar() {
		lista = categorias.lista();
	}

	public void novo() {
		categoria = new Categoria();
	}

	public void salvar() {
		try {
			categoria = categorias.guardar(categoria);
			FacesUtil.addInfoMessage("Gravado com sucesso");
		} catch (Exception e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public void excluir(Categoria categoria) {
		try {
			categorias.remover(categoria);
			FacesUtil.addInfoMessage("Registro excluído com sucesso.");
			pesquisar();
		} catch (Exception e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Categoria> getLista() {
		return lista;
	}
}
