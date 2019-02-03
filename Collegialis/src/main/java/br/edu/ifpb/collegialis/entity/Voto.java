package br.edu.ifpb.collegialis.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.edu.ifpb.collegialis.type.TipoVoto;

@Entity
@Table(name="TB_VOTO")
public class Voto {
	@Id
	@Column(name="NU_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="TP_VOTO")
	@Enumerated(EnumType.STRING)
	private TipoVoto voto;
	
	@Column(name="ST_AUSENTE")
	private boolean ausente;
	
	@OneToOne
	@JoinColumn(name = "ID_MEMBRO", foreignKey=@ForeignKey(name = "FK_MEMBRO"))
	private Professor membro;
	
	@OneToOne
	@JoinColumn(name = "ID_REPRES", foreignKey=@ForeignKey(name = "FK_REPRES"))
	private Aluno representante;
	
	@ManyToOne
	@JoinColumn(name = "ID_PROCESSO", foreignKey=@ForeignKey(name = "FK_PROCESSO"))
	private Processo processo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TipoVoto getVoto() {
		return voto;
	}

	public void setVoto(TipoVoto voto) {
		this.voto = voto;
	}

	public boolean isAusente() {
		return ausente;
	}

	public void setAusente(boolean ausente) {
		this.ausente = ausente;
	}

	public Professor getMembro() {
		return membro;
	}

	public void setMembro(Professor membro) {
		this.membro = membro;
	}

	public Processo getProcesso() {
		return processo;
	}

	public void setProcesso(Processo processo) {
		this.processo = processo;
	}

	public Aluno getRepresentante() {
		return representante;
	}

	public void setRepresentante(Aluno representante) {
		this.representante = representante;
	}
}
