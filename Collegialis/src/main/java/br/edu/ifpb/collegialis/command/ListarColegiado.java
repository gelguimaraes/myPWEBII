package br.edu.ifpb.collegialis.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifpb.collegialis.dao.ColegiadoDAO;
import br.edu.ifpb.collegialis.entity.Colegiado;

public class ListarColegiado implements IComando {

	@Override
	public Resultado execute(HttpServletRequest request, HttpServletResponse response) {

		Resultado resultado = new Resultado();

		ColegiadoDAO dao = new ColegiadoDAO();
		List<Colegiado> colegiados = dao.findAll();
		
		request.setAttribute("listaColegiado", colegiados);

		resultado.setErro(false);
		resultado.setRedirect(false);
		resultado.setProximaPagina("listaColegiado.jsp");

		return resultado;
	}
}