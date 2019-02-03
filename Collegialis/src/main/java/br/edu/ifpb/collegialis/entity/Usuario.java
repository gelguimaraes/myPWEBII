package br.edu.ifpb.collegialis.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TB_USUARIO")
public class Usuario {
	
	
	protected String login;
	

	protected String senha;
	
	@Id
	@Column(name="NU_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Integer id;
	

	
	public Usuario(String login, String senha) {
		super();
		this.login = login;
		this.senha = senha;
	}
	public Usuario() {}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	


}
