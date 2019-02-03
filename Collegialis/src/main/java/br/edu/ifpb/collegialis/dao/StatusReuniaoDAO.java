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

import br.edu.ifpb.collegialis.entity.Reuniao;
import br.edu.ifpb.collegialis.type.StatusReuniao;

public class StatusReuniaoDAO extends GenericDAO<Reuniao, Integer> implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public StatusReuniaoDAO(){
		super(PersistenceUtil.getCurrentEntityManager());	
	}
	
	public StatusReuniaoDAO(EntityManager em) {
		super(em);
	}
	
	@SuppressWarnings("unchecked")
	public List<StatusReuniao> findAll() throws DAOException {
		try {
		Query q = this.getEntityManager().createQuery("from StatusReuniao p order by p.texto asc");
		return (List<StatusReuniao>) q.getResultList();
		} catch(PersistenceException e) {
			throw new DAOException("N�o foi poss�vel obter todos os status do banco. " + e.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Reuniao> consultaPorStatus(String status) {
		try {
			Query q = this.getEntityManager().createQuery("from StatusReuniao r where upper(r.texto) like :status order by p.status asc");
			q.setParameter("status", status.toUpperCase());
			return (List<Reuniao>) q.getResultList();
		} catch (PersistenceException e) {
			throw new DAOException("N�o foi poss�vel obter todos as reunioes com status X do banco. " + e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	public List<StatusReuniao> findDoStatusReuniao(StatusReuniao statusReuniao) {
		try {
			Query q = this.getEntityManager().createQuery("from StatusReuniao p where p.texto = :texto order by p.texto asc");
			q.setParameter("status", statusReuniao);
			return (List<StatusReuniao>) q.getResultList();
			} catch(PersistenceException e) {
				throw new DAOException("N�o foi poss�vel obter todos os status do banco. " + e.getMessage());
			}
	}
	
	public List<StatusReuniao> findAll(StatusReuniao statusReuniao) {
		CriteriaQuery<StatusReuniao> criteria = makeCriteria(statusReuniao);
		TypedQuery<StatusReuniao> query = this.getEntityManager().createQuery(criteria);
		return query.getResultList();
	}
	
	private CriteriaQuery<StatusReuniao> makeCriteria(StatusReuniao statusReuniao) {
		final List<Predicate> andPredicates = new ArrayList<Predicate>();
		CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<StatusReuniao> cq = cb.createQuery(StatusReuniao.class);
		Root<StatusReuniao> root = cq.from(StatusReuniao.class);
		cq.select(root);

		if (statusReuniao.getTexto() != null) {
			Path<StatusReuniao> status = root.get("status");
			andPredicates.add(cb.equal(status, statusReuniao.getTexto()));
		}
		if (andPredicates.size() > 0) {
		    cq.where(andPredicates.toArray(new Predicate[andPredicates.size()]));
		}
		return cq;
	}

}
