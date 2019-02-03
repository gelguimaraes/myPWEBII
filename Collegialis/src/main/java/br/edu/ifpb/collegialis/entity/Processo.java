package br.edu.ifpb.collegialis.entity;


import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollectionOption;

import br.edu.ifpb.collegialis.type.TipoDecisao;
import br.edu.ifpb.collegialis.type.TipoStatusProcesso;

@Entity
@Table(name="TB_PROCESSO")
public class Processo {
	@Id
	@Column(name="NU_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="NU_PROCESSO")
	private String numero;
	
	@Column(name="DT_RECEPCAO")
	private Date dataRecepcao;
	
	@Column(name="DT_DISTRIBUICAO")
	private Date dataDistribuicao;
	
	@Column(name="DT_PARECER")
	private Date dataParecer;
	
	@Column(name="LB_REQUERIMENTO")
	private String requerimento;
	
	@Column(name="LB_PARECER")
	private String parecer;
	
	@Column(name="TP_DECISAO")
	@Enumerated(EnumType.STRING)
	private TipoDecisao decisao;
		
	@Column(name="ST_STATUS")
	@Enumerated(EnumType.STRING)
	private TipoStatusProcesso status;
	
	
	@OneToOne
	@JoinColumn(name="ID_ASSUNTO", foreignKey=@ForeignKey(name = "FK_ASSUNTO"))
	private Assunto assunto;
	
	@OneToMany (mappedBy="processo")
	@org.hibernate.annotations.LazyCollection(LazyCollectionOption.EXTRA)
	private Set<Voto> votos;
	
	@OneToOne
	@JoinColumn(name="ID_RELATOR", foreignKey=@ForeignKey(name = "FK_RELATOR"))
	private Professor relator;
	
	@OneToOne
	@JoinColumn(name="ID_REQUISITANTE", foreignKey=@ForeignKey(name = "FK_REQUISITANTE"))
	private Aluno requisitante;
	
	@ManyToOne
	@org.hibernate.annotations.LazyCollection(LazyCollectionOption.EXTRA)
	@JoinColumn(name = "ID_REUNIAO", foreignKey=@ForeignKey(name = "FK_REUNIAO"))
	private Reuniao reuniao;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Date getDataRecepcao() {
		return dataRecepcao;
	}

	public void setDataRecepcao(Date dataRecepcao) {
		this.dataRecepcao = dataRecepcao;
	}

	public Date getDataDistribuicao() {
		return dataDistribuicao;
	}

	public void setDataDistribuicao(Date dataDistribuicao) {
		this.dataDistribuicao = dataDistribuicao;
	}

	public Date getDataParecer() {
		return dataParecer;
	}

	public void setDataParecer(Date dataParecer) {
		this.dataParecer = dataParecer;
	}

	public String getParecer() {
		return parecer;
	}

	public void setParecer(String parecer) {
		this.parecer = parecer;
	}

	public TipoDecisao getDecisao() {
		return decisao;
	}

	public void setDecisao(TipoDecisao decisao) {
		this.decisao = decisao;
	}
	
	public TipoStatusProcesso getStatus() {
		return status;
	}

	public void setStatus(TipoStatusProcesso status) {
		this.status = status;
	}

	public Assunto getAssunto() {
		return assunto;
	}

	public void setAssunto(Assunto assunto) {
		this.assunto = assunto;
	}

	public Set<Voto> getVotos() {
		return votos;
	}

	public void setVotos(Set<Voto> votos) {
		this.votos = votos;
	}


	public void setReuniao(Reuniao reuniao) {
		this.reuniao = reuniao;
	}

	public Reuniao getReuniao() {
		return reuniao;
	}

	public Professor getRelator() {
		return relator;
	}

	public void setRelator(Professor relator) {
		this.relator = relator;
	}

	public Aluno getRequisitante() {
		return requisitante;
	}

	public void setRequisitante(Aluno requisitante) {
		this.requisitante = requisitante;
	}

	public String getRequerimento() {
		return requerimento;
	}

	public void setRequerimento(String requerimento) {
		this.requerimento = requerimento;
	}

}
