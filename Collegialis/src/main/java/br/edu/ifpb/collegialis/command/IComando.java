package br.edu.ifpb.collegialis.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifpb.collegialis.command.Resultado;

public interface IComando {
	
	Resultado execute(HttpServletRequest request, HttpServletResponse response);

}
