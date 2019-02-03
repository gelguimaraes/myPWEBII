package br.edu.ifpb.collegialis.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifpb.collegialis.dao.ProcessoDAO;
import br.edu.ifpb.collegialis.entity.Processo;

public class ListarProcessos implements IComando {

	@Override
	public Resultado execute(HttpServletRequest request, HttpServletResponse response) {

		Resultado resultado = new Resultado();

		ProcessoDAO dao = new ProcessoDAO();
		List<Processo> processos = dao.findAll();
		
		request.setAttribute("listaProcesso", processos);

		resultado.setErro(false);
		resultado.setRedirect(false);
		resultado.setProximaPagina("listaProcesso.jsp");

		return resultado;
	}
}