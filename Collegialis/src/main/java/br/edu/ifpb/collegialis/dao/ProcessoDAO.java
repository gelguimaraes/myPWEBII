package br.edu.ifpb.collegialis.dao;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
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

import br.edu.ifpb.collegialis.entity.Processo;


public class ProcessoDAO extends GenericDAO<Processo, Integer>  implements Serializable{
	private static final long serialVersionUID = 1L;
	public ProcessoDAO() {
		super(PersistenceUtil.getCurrentEntityManager());
	}

	public ProcessoDAO(EntityManager em) {
		super(em);
	}

	@SuppressWarnings("unchecked")
	public List<Processo> find(Integer[] ids) {
		Query q = this.getEntityManager().createQuery("from Processo where id IN :ids");
		q.setParameter("ids", Arrays.asList(ids));
		return q.getResultList();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Processo> findAll() throws DAOException {
		try {
		Query q = this.getEntityManager().createQuery("from Processo p order by p.id desc");
		return (List<Processo>) q.getResultList();
		} catch(PersistenceException e) {
			throw new DAOException("N�o foi poss�vel obter todos os Processo do banco. " + e.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Processo> findProcessoCurso(String curso) {
		try {
			Query q = this.getEntityManager().createQuery("select p from Processo p join p.relator r join r.colegiados c  where c.curso = :curso order by p.id desc");
			q.setParameter("curso", curso);
			return (List<Processo>) q.getResultList();
			} catch(PersistenceException e) {
				throw new DAOException("N�o foi poss�vel obter todos os Processo do banco. " + e.getMessage());
			}
	}
	
	@SuppressWarnings("unused")
	private CriteriaQuery<Processo> makeCriteria(String curso) {
		final List<Predicate> andPredicates = new ArrayList<Predicate>();
		CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Processo> cq = cb.createQuery(Processo.class);
		Root<Processo> root = cq.from(Processo.class);
		cq.select(root);
		
		if (curso != null) {
			Path<String> cursor = root.get("curso");
			andPredicates.add(cb.equal(cursor, curso));
		}
	
		
		if (andPredicates.size() > 0) {
		    // no need to make new predicate, it is already a conjunction
		    cq.where(andPredicates.toArray(new Predicate[andPredicates.size()]));
		}
		return cq;
	}

}