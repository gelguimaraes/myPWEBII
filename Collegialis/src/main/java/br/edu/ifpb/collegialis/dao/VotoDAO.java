package br.edu.ifpb.collegialis.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;

import br.edu.ifpb.collegialis.entity.Voto;

public class VotoDAO extends GenericDAO<Voto, Integer> implements Serializable{

	public VotoDAO() {
		super(PersistenceUtil.getCurrentEntityManager());
	}

	public VotoDAO(EntityManager em) {
		super(em);
	}
	

}
