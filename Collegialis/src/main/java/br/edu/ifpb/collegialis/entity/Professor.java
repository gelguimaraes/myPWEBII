package br.edu.ifpb.collegialis.entity;


import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="TB_PROFESSOR")
public class Professor extends Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public Professor(String login, String senha) {
		super(login, senha);
	}
	public Professor() {}
	
	@Column(name="DS_NOME")
	private String nome;
	
	@Column(name="NU_MATRICULA", length=11)
	private String matricula;
	
	@Column(name="DS_EMAIL")
	private String email;
	
	@Column(name="NU_FONE")
	private String fone;
	
	@Column(name="BO_COORDENADOR")
	private Boolean coordenador = false;
	
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	public Boolean isCoordenador() {
		return coordenador;
	}
	public void setCoordenador(Boolean coordenador) {
		this.coordenador = coordenador;
	}

	@ManyToMany(mappedBy = "membros")
	private List<Colegiado> colegiados;
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Professor other = (Professor) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	public List<Colegiado> getColegiados() {
		return colegiados;
	}

	public void setColegiados(List<Colegiado> colegiados) {
		this.colegiados = colegiados;
	}
	


}
