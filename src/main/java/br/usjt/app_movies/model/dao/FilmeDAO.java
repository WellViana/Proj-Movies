package br.usjt.app_movies.model.dao;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.usjt.app_movies.model.entity.Filme;

@Repository
public class FilmeDAO {
	@PersistenceContext
	EntityManager manager;

	public int inserirFilme(Filme filme) throws IOException {
		manager.persist(filme);
		return filme.getId();
	}

	public Filme buscarFilme(int id) throws IOException {
		return manager.find(Filme.class, id);
	}

	public void atualizarFilme(Filme filme) throws IOException {
		manager.merge(filme);
	}

	public void excluirFilme(int id) throws IOException {
		manager.remove(manager.find(Filme.class, id));
	}

	@SuppressWarnings("unchecked")
	public List<Filme> listarFilmes(String chave) throws IOException {
		Query query = manager.createQuery("select f from Filme f where f.titulo like :chave");
		query.setParameter("chave", "%" + chave + "%");
		return query.getResultList();

	}

	@SuppressWarnings("unchecked")
	public List<Filme> listarFilmes() throws IOException {
		return manager.createQuery("select f from Filme f").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Filme> listarNovosFilmes() throws IOException {
		return manager.createQuery("select f from Filme f order by f.id desc").getResultList();
	}

}
