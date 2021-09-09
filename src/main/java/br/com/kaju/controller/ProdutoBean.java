package br.com.kaju.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.kaju.model.Categoria;
import br.com.kaju.model.CondicaoFilter;
import br.com.kaju.model.Produto;
import br.com.kaju.repository.Categorias;
import br.com.kaju.repository.Produtos;
import br.com.kaju.util.jsf.FacesUtil;

@Named
@ViewScoped
public class ProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Categorias categorias;
	@Inject
	private Produtos produtos;

	private CondicaoFilter filtro;
	private Produto produto;
	private List<Produto> lista;
	private List<Categoria> listaCategoria;

	public ProdutoBean() {
		produto = new Produto();
		filtro = new CondicaoFilter();
	}

	public void inicializar() {
		if (FacesUtil.isNotPostback()) {
			listaCategoria = categorias.lista();
		}
	}

	public void salvar() {
		try {
			produto = produtos.guardar(produto);
			FacesUtil.addInfoMessage("Gravado com sucesso");
		} catch (Exception e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public void excluir(Produto categoria) {
		try {
			produtos.remover(categoria);
			FacesUtil.addInfoMessage("Registro excluído com sucesso.");
			pesquisar();
		} catch (Exception e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public void pesquisar() {
		lista = produtos.lista(filtro);
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getLista() {
		return lista;
	}

	public List<Categoria> getListaCategoria() {
		return listaCategoria;
	}

	public CondicaoFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(CondicaoFilter filtro) {
		this.filtro = filtro;
	}

}