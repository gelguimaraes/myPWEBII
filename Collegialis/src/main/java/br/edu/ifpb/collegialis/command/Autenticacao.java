package br.edu.ifpb.collegialis.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.ifpb.collegialis.dao.UsuarioDAO;
import br.edu.ifpb.collegialis.entity.Aluno;
import br.edu.ifpb.collegialis.entity.Professor;
import br.edu.ifpb.collegialis.entity.Usuario;

public class Autenticacao implements IComando {

	@Override
	public Resultado execute(HttpServletRequest request, HttpServletResponse response) {

		Resultado resultado = new Resultado();

		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		HttpSession session = request.getSession();
		
		if(login != "" && senha != "") {
			UsuarioDAO dao = new UsuarioDAO();
			Usuario u = dao.Login(login, senha);
		
			if(u == null) {
				resultado.setErro(true);
				resultado.setRedirect(true);
				resultado.addMensagem("Login ou Senha Inválido!");
				resultado.setProximaPagina("login.jsp");
			}else {
				session.setAttribute("logado", "logado");
				session.setAttribute("idUser", u.getId());
				if(u instanceof Aluno) {
					session.setAttribute("nameUser", ((Aluno) u).getNome());
					if(((Aluno) u).getColegiado()!=null) {
						session.setAttribute("typeUser", "Representante");
					} else {
						session.setAttribute("typeUser", "Aluno");
					}
						
				}else if(u instanceof Professor) {
					session.setAttribute("nameUser", ((Professor) u).getNome());
			
					if(((Professor) u).isCoordenador() == true) {
						session.setAttribute("typeUser", "Coordenador");
					} else {
						session.setAttribute("typeUser", "Professor");
					}
					
				}
				resultado.setRedirect(true);
				resultado.setProximaPagina("home.jsp");
			}
		}
		else {
			resultado.setErro(true);
			resultado.addMensagem("Login Inválido!");
			resultado.setRedirect(true);
			resultado.setProximaPagina("login.jsp");	
		}

		return resultado;
	}
}