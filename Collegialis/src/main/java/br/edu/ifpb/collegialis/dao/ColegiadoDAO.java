package br.edu.ifpb.collegialis.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.edu.ifpb.collegialis.entity.Colegiado;
import br.edu.ifpb.collegialis.dao.PersistenceUtil;

public class ColegiadoDAO extends GenericDAO<Colegiado, Integer> implements Serializable {
	private static final long serialVersionUID = 1L;

	public ColegiadoDAO() {
		super(PersistenceUtil.getCurrentEntityManager());
	}

	public ColegiadoDAO(EntityManager em) {
		super(em);
	}

	@SuppressWarnings("unchecked")
	public List<Colegiado> findAll() throws DAOException {
		try {
			Query q = this.getEntityManager().createQuery("from Colegiado c order by c.curso asc");
			return (List<Colegiado>) q.getResultList();
		} catch (PersistenceException e) {
			throw new DAOException("N�o foi poss�vel obter todos os colegiados do banco. " + e.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Colegiado> findAllAtivos() throws DAOException {
		try {
			Query q = this.getEntityManager().createQuery("from Colegiado c where c.ativo = :ativo order by c.dataInicio desc");
			q.setParameter("ativo", true);
			return (List<Colegiado>) q.getResultList();
		} catch (PersistenceException e) {
			throw new DAOException("N�o foi poss�vel obter todos os coordenadores do banco. " + e.getMessage());
		}
	}

	
	public List<Colegiado> findAll(Colegiado colegiado, boolean soAtivos) {
		CriteriaQuery<Colegiado> criteria = makeCriteria(colegiado, soAtivos);
		TypedQuery<Colegiado> query = this.getEntityManager().createQuery(criteria);
		return query.getResultList();
	}
	
	private CriteriaQuery<Colegiado> makeCriteria(Colegiado colegiado, boolean soColegiadosAtivos) {
		final List<Predicate> andPredicates = new ArrayList<Predicate>();
		CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Colegiado> cq = cb.createQuery(Colegiado.class);
		Root<Colegiado> root = cq.from(Colegiado.class);
		cq.select(root);

		if (soColegiadosAtivos) {
			Path<Boolean> curso = root.get("ativo");
			andPredicates.add(cb.equal(curso, true));
		}
		if (andPredicates.size() > 0) {
		    // no need to make new predicate, it is already a conjunction
		    cq.where(andPredicates.toArray(new Predicate[andPredicates.size()]));
		}
		return cq;
	}
}
