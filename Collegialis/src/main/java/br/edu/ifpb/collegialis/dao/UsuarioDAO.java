package br.edu.ifpb.collegialis.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import br.edu.ifpb.collegialis.entity.Aluno;
import br.edu.ifpb.collegialis.entity.Usuario;


public class UsuarioDAO extends GenericDAO<Usuario, Integer> implements Serializable{
	private static final long serialVersionUID = 1L;

	public UsuarioDAO() {
		super(PersistenceUtil.getCurrentEntityManager());
	}

	
	public UsuarioDAO(EntityManager em) {
		super(em);
	}
	
	@SuppressWarnings("unchecked")
	public Usuario Login(String login, String senha) throws DAOException, NoResultException {
		
		try {
			Query q = this.getEntityManager().createQuery("from Usuario u where u.login = :login and u.senha = :senha");
			q.setParameter("login", login);
            q.setParameter("senha", senha);
            return (Usuario) q.getSingleResult();	
		} catch (NoResultException e) {
			return null;
		} 
	}
	@SuppressWarnings("unchecked")
	public List<Usuario> findAll() throws DAOException {
		try {
		Query q = this.getEntityManager().createQuery("from Usuario u order by u.login asc");
		return (List<Usuario>) q.getResultList();
		} catch(PersistenceException e) {
			throw new DAOException("Não foi possível obter todos os usuarios do banco. " + e.getMessage());
		}
	}



}
