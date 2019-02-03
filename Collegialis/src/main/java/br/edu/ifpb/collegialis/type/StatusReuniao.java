package br.edu.ifpb.collegialis.type;

public enum StatusReuniao {
	ENCERRADA("Encerrada"),
	PLANEJADA("Planejada");
	
	private String texto;
	
	private StatusReuniao(String texto) {
		this.texto = texto;
	}

	public String getTexto() {
		return texto;
	}
	
	public void setTexto(String texto) {
		this.texto = texto;
	}

}
