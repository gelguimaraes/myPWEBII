package br.edu.ifpb.collegialis.type;

public enum TipoVoto {
	DEFERIDO("Deferido"),
	INDEFERIDO("Indeferido");
	
	private String texto;
	
	private TipoVoto(String texto) {
		this.texto = texto;
	}

	public String getTexto() {
		return texto;
	}

}
