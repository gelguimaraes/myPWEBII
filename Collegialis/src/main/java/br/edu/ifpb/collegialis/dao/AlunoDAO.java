package br.edu.ifpb.collegialis.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import br.edu.ifpb.collegialis.entity.Aluno;
import br.edu.ifpb.collegialis.entity.Assunto;
import br.edu.ifpb.collegialis.entity.Professor;

public class AlunoDAO extends GenericDAO<Aluno, Integer> implements Serializable{
	private static final long serialVersionUID = 1L;

	public AlunoDAO() {
		super(PersistenceUtil.getCurrentEntityManager());
	}

	
	public AlunoDAO(EntityManager em) {
		super(em);
	}
	
	@SuppressWarnings("unchecked")
	public List<Aluno> findAll() throws DAOException {
		try {
		Query q = this.getEntityManager().createQuery("from Aluno a order by a.nome asc");
		return (List<Aluno>) q.getResultList();
		} catch(PersistenceException e) {
			throw new DAOException("Não foi possível obter todos os alunos do banco. " + e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	public List<Aluno> consultaPorNome(String nome) {
		try {
			Query q = this.getEntityManager().createQuery("from Aluno a where upper(a.nome) like :nome order by a.nome asc");
			q.setParameter("nome", nome.toUpperCase());
			return (List<Aluno>) q.getResultList();
		} catch (PersistenceException e) {
			throw new DAOException("Não foi possível obter todos os alunos com nome X do banco. " + e.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Aluno> consultaPorMatricula(String matricula) {
		try {
			Query q = this.getEntityManager().createQuery("from Aluno a where a.matricula like :matricula order by a.nome asc");
			q.setParameter("matricula", matricula);
			return (List<Aluno>) q.getResultList();
		} catch (PersistenceException e) {
			return null;
		}
	}
	
	public Aluno findRepresCurso(String curso) {
		try {
		Query q = this.getEntityManager().createQuery("select r from Colegiado c JOIN c.representante r where c.curso = :curso order by r.nome asc");
		q.setParameter("curso", curso);
		return (Aluno) q.getSingleResult();
		} catch(PersistenceException e) {
			throw new DAOException("NÃ£oï¿½o foi possÃ­vel obter todos os professores do banco. " + e.getMessage());
		}
	}
	

}
