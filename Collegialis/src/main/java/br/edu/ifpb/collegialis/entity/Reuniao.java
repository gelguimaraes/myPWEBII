package br.edu.ifpb.collegialis.entity;


import java.util.Date;
import java.util.List;

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
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollectionOption;

import br.edu.ifpb.collegialis.type.StatusReuniao;

@Entity
@Table(name="TB_REUNIAO")
public class Reuniao {
	@Id
	@Column(name="NU_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="DT_REUNIAO")
	private Date data;
	
	@Column(name = "DS_DESCRICAO")
	private String descricao;
	
	@Column(name="TP_STATUS")
	@Enumerated(EnumType.STRING)
	private StatusReuniao status;
	
	@Lob
	@Column(name="LB_ATA")
	private byte[] ata;
	
	@ManyToOne
	@JoinColumn(name="ID_COLEGIADO", foreignKey=@ForeignKey(name = "FK_COLEGIADO"))
	private Colegiado colegiado;
	
	@OneToMany(mappedBy="reuniao")
	@org.hibernate.annotations.LazyCollection(LazyCollectionOption.EXTRA)
	private List<Processo> processos;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public StatusReuniao getStatus() {
		return status;
	}

	public void setStatus(StatusReuniao status) {
		this.status = status;
	}

	public byte[] getAta() {
		return ata;
	}

	public void setAta(byte[] ata) {
		this.ata = ata;
	}

	public Colegiado getColegiado() {
		return colegiado;
	}

	public void setColegiado(Colegiado colegiado) {
		this.colegiado = colegiado;
	}

	public List<Processo> getProcessos() {
		return processos;
	}

	public void setProcessos(List<Processo> processos) {
		this.processos = processos;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
