package br.edu.ifpb.collegialis.entity;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="TB_COLEGIADO")
public class Colegiado  {
	@Id
	@Column(name="NU_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="DT_INICIO")
	private Date dataInicio;
	
	@Column(name="DT_FIM")
	private Date dataFim;
	
	@Column(name="DS_DESCRICAO")
	private String descricao;
	
	@Column(name="NU_PROTARIA")
	private String portaria;
	
	@Column(name="NM_CURSO")
	private String curso;

	@OneToMany (mappedBy="colegiado", cascade=CascadeType.ALL)
	@org.hibernate.annotations.LazyCollection(LazyCollectionOption.EXTRA)
	private List<Reuniao> reunioes = new ArrayList<Reuniao>();
	
	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "TB_Membro", 
        joinColumns = { @JoinColumn(name = "colegiado_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "professor_id") }
    )
	@org.hibernate.annotations.LazyCollection(LazyCollectionOption.EXTRA)
	private List<Professor> membros = new ArrayList<Professor>();
	
	@OneToOne
	@JoinColumn(name = "ID_REPRES", foreignKey=@ForeignKey(name = "FK_REPRESENTANTE"))
	private Aluno representante;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getPortaria() {
		return portaria;
	}

	public void setPortaria(String portaria) {
		this.portaria = portaria;
	}

	public List<Reuniao> getReunioes() {
		return reunioes;
	}

	public void setReunioes(List<Reuniao> reunioes) {
		this.reunioes = reunioes;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((curso == null) ? 0 : curso.hashCode());
		result = prime * result + ((dataFim == null) ? 0 : dataFim.hashCode());
		result = prime * result + ((dataInicio == null) ? 0 : dataInicio.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Colegiado other = (Colegiado) obj;
		if (curso == null) {
			if (other.curso != null)
				return false;
		} else if (!curso.equals(other.curso))
			return false;
		if (dataFim == null) {
			if (other.dataFim != null)
				return false;
		} else if (!dataFim.equals(other.dataFim))
			return false;
		if (dataInicio == null) {
			if (other.dataInicio != null)
				return false;
		} else if (!dataInicio.equals(other.dataInicio))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	public void addReuniao(Reuniao reuniao) {
		this.reunioes.add(reuniao);
		reuniao.setColegiado(this);
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public List<Professor> getMembros() {
		return membros;
	}

	public void setMembros(List<Professor> membros) {
		this.membros = membros;
	}

	public Aluno getRepresentante() {
		return representante;
	}

	public void setRepresentante(Aluno representante) {
		this.representante = representante;
	}

	
	
}
