package br.edu.ifpb.collegialis.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import br.edu.ifpb.collegialis.entity.Assunto;

public class AssuntoDAO extends GenericDAO<Assunto, Integer> implements Serializable{
	private static final long serialVersionUID = 1L;

	public AssuntoDAO() {
		super(PersistenceUtil.getCurrentEntityManager());
	}

	public AssuntoDAO(EntityManager em) {
		super(em);
	}
	
	@SuppressWarnings("unchecked")
	public List<Assunto> findAll() throws DAOException {
		try {
		Query q = this.getEntityManager().createQuery("from Assunto a order by a.descricao asc");
		return (List<Assunto>) q.getResultList();
		} catch(PersistenceException e) {
			throw new DAOException("Não foi possível obter todos os assuntos do banco. " + e.getMessage());
		}
	}
}
