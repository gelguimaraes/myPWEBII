package br.edu.ifpb.collegialis.type;

public enum TipoStatusProcesso {
	JULGADO("Julgado"),
	EMPAUTA("Em pauta"),
	RETIRADO("Retirado de pauta"),
	ABERTO("Em Aberto");
	
	private String texto;

	public String getTexto() {
		return texto;
	}
	
	private TipoStatusProcesso(String texto) {
		this.texto = texto;
	}

}
