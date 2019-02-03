package br.edu.ifpb.collegialis.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.ifpb.collegialis.dao.ColegiadoDAO;
import br.edu.ifpb.collegialis.entity.Colegiado;

public class Logout implements IComando {

	@Override
	public Resultado execute(HttpServletRequest request, HttpServletResponse response) {

		Resultado resultado = new Resultado();
		HttpSession s = request.getSession(false);
		s.removeAttribute("login");
		resultado.setErro(false);
		resultado.setRedirect(true);
		resultado.setProximaPagina("login.jsp");

		return resultado;
	}
}