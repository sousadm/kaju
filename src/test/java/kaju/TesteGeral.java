package kaju;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.kaju.model.Categoria;

public class TesteGeral {
	public static void main(String[] args) {

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("SistemaPU");
		EntityManager manager = factory.createEntityManager();

		List<Categoria> lista = manager.createNamedQuery("Categoria.lista", Categoria.class).getResultList();
		for (Categoria categoria : lista) {
			System.out.println(categoria.getDescricao());
		}
	}
}
