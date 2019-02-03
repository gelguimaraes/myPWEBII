package br.edu.ifpb.collegialis.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.ifpb.collegialis.dao.ReuniaoDAO;
import br.edu.ifpb.collegialis.entity.Reuniao;

public class ListarReuniao implements IComando {

	@Override
	public Resultado execute(HttpServletRequest request, HttpServletResponse response) {

		Resultado resultado = new Resultado();
		
		ReuniaoDAO dao = new ReuniaoDAO();
		List<Reuniao> reunioes = dao.findAll();
		
		request.setAttribute("listaReuniao", reunioes);
		resultado.setProximaPagina("listaReunioes.jsp");

		return resultado;
	}
}