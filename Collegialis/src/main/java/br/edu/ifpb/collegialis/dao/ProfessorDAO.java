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
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.edu.ifpb.collegialis.entity.Professor;

public class ProfessorDAO extends GenericDAO<Professor, Integer> implements Serializable{
	private static final long serialVersionUID = 1L;

	public ProfessorDAO() {
		super(PersistenceUtil.getCurrentEntityManager());
	}

	public ProfessorDAO(EntityManager em) {
		super(em);
	}
	
	@SuppressWarnings("unchecked")
	public List<Professor> findAll() throws DAOException {
		try {
		Query q = this.getEntityManager().createQuery("from Professor p order by p.nome asc");
		return (List<Professor>) q.getResultList();
		} catch(PersistenceException e) {
			throw new DAOException("Não foi possível obter todos os professores do banco. " + e.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Professor> consultaPorNome(String nome) {
		try {
			Query q = this.getEntityManager().createQuery("from Professor p where upper(p.nome) like :nome order by p.nome asc");
			q.setParameter("nome", nome.toUpperCase());
			return (List<Professor>) q.getResultList();
		} catch (PersistenceException e) {
			throw new DAOException("Não foi possível obter todos os professor com nome X do banco. " + e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	public List<Professor> findDoCurso(String curso) {
		try {
			Query q = this.getEntityManager().createQuery("select m from Colegiado c JOIN c.membros m where c.curso = :curso order by m.nome asc");
			q.setParameter("curso", curso);
			return (List<Professor>) q.getResultList();
			} catch(PersistenceException e) {
				throw new DAOException("Não�o foi possível obter todos os professores do banco. " + e.getMessage());
			}
	}
	
	public List<Professor> findAll(Professor professor) {
		CriteriaQuery<Professor> criteria = makeCriteria(professor);
		TypedQuery<Professor> query = this.getEntityManager().createQuery(criteria);
		return query.getResultList();
	}
	
	private CriteriaQuery<Professor> makeCriteria(Professor professor) {
		final List<Predicate> andPredicates = new ArrayList<Predicate>();
		CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Professor> cq = cb.createQuery(Professor.class);
		Root<Professor> root = cq.from(Professor.class);
		cq.select(root);

		if (andPredicates.size() > 0) {
		    cq.where(andPredicates.toArray(new Predicate[andPredicates.size()]));
		}
		return cq;
	}

}
