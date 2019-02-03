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
import br.edu.ifpb.collegialis.entity.Reuniao;
import br.edu.ifpb.collegialis.type.StatusReuniao;

public class ReuniaoDAO extends GenericDAO<Reuniao, Integer> implements Serializable{
	private static final long serialVersionUID = 1L;

	public ReuniaoDAO() {
		super(PersistenceUtil.getCurrentEntityManager());
	}

	public ReuniaoDAO(EntityManager em) {
		super(em);
	}
	
	@SuppressWarnings("unchecked")
	public List<Reuniao> findAll() throws DAOException {
		try {
		Query q = this.getEntityManager().createQuery("from Reuniao p order by p.data desc");
		return (List<Reuniao>) q.getResultList();
		} catch(PersistenceException e) {
			throw new DAOException("N�o foi poss�vel obter todos as reunioes do banco. " + e.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Reuniao> consultaPorStatus(String status) {
		try {
			Query q = this.getEntityManager().createQuery("from Reuniao r where upper(r.status) like :status order by p.status asc");
			q.setParameter("status", status.toUpperCase());
			return (List<Reuniao>) q.getResultList();
		} catch (PersistenceException e) {
			throw new DAOException("N�o foi poss�vel obter todos as reunioes com status X do banco. " + e.getMessage());
		}
	}

	public List<Reuniao> findDoColegiado(Colegiado colegiado) {
		try {
			Query q = this.getEntityManager().createQuery("from Reuniao p where p.colegiado = :colegiado order by p.nome asc");
			q.setParameter("colegiado", colegiado);
			return (List<Reuniao>) q.getResultList();
			} catch(PersistenceException e) {
				throw new DAOException("N�o foi poss�vel obter todas as reunioes do banco. " + e.getMessage());
			}
	}
	
	public List<Reuniao> findAll(Reuniao reuniao) {
		CriteriaQuery<Reuniao> criteria = makeCriteria(reuniao);
		TypedQuery<Reuniao> query = this.getEntityManager().createQuery(criteria);
		return query.getResultList();
	}
	
	private CriteriaQuery<Reuniao> makeCriteria(Reuniao reuniao) {
		final List<Predicate> andPredicates = new ArrayList<Predicate>();
		CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Reuniao> cq = cb.createQuery(Reuniao.class);
		Root<Reuniao> root = cq.from(Reuniao.class);
		cq.select(root);

		if (reuniao.getColegiado() != null) {
			Path<Colegiado> colegiado = root.get("colegiado");
			andPredicates.add(cb.equal(colegiado, reuniao.getColegiado()));
		}
		if (reuniao.getStatus() != null) {
			Path<StatusReuniao> sr = root.get("status");
			andPredicates.add(cb.equal(sr, reuniao.getStatus()));
		}
		if (andPredicates.size() > 0) {
		    cq.where(andPredicates.toArray(new Predicate[andPredicates.size()]));
		}
		return cq;
	}

}

