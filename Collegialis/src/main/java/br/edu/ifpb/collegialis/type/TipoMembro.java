package br.edu.ifpb.collegialis.type;

public enum TipoMembro {
	PROFESSOR("Professor"),
	ALUNO("Aluno");
	
	private String texto;
	
	private TipoMembro(String texto) {
		this.texto = texto;
	}

	public String getTexto() {
		return texto;
	}

}
