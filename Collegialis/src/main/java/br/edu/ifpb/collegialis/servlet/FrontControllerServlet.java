package br.edu.ifpb.collegialis.servlet;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.ifpb.collegialis.command.IComando;
import br.edu.ifpb.collegialis.command.Resultado;

/**
 * Servlet que atende a todas as requisi√ß√µes dos diversos casos de uso da
 * aplica√ß√£o. Possui um par√¢metro obrigat√≥rio onde deve ser informada a
 * opera√ß√£o a ser executada.
 */
@WebServlet("/controller.do")
public class FrontControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String NOME_PACOTE = "br.edu.ifpb.collegialis.command.";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doRequest(request, response);
	}

	protected void doRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		// Descobre a classe de comando a ser executada.
		Properties comandos = (Properties) request.getServletContext().getAttribute("comandos");

		String operacao = request.getParameter("op");
		if (operacao == null) {
			request.setAttribute("_erro", "OperaÁ„o (op) n„o especificada na requisiÁ„o!");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} else {
			//System.out.println(session.getAttribute("logado"));
			if (session.getAttribute("logado") != "logado" && !(operacao.equals("autentica"))) {
				request.setAttribute("_erro", "Sess„o inv·lida!");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				return;
			}
		}

		Resultado resultado = null;
		String nomeClasseComando = comandos.getProperty(operacao);
		try {
			Class<?> clazzComando = Class.forName(NOME_PACOTE + nomeClasseComando);
			IComando comando = (IComando) clazzComando.newInstance();
			resultado = comando.execute(request, response);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			request.setAttribute("_erro", "Comando " + operacao + " inexistente!");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		} catch (Exception e) {
			request.setAttribute("_erro", "Erro inesperado: " + e.getMessage());
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}

		if (resultado.isErro()) {
			session.removeAttribute("_msg");
			session.setAttribute("_erro", resultado.getMensagens());
			//System.out.println("caiu no erro");
			// this.getServletContext().setAttribute("_erro", resultado.getMensagens());
		} else {
			session.removeAttribute("_erro");
			session.setAttribute("_msg", resultado.getMensagens());
			//System.out.println("nao caiu no erro");
			// this.getServletContext().setAttribute("_msg", resultado.getMensagens());
		}

		if (resultado.isRedirect()) {
			response.sendRedirect(resultado.getProximaPagina());
		} else {
			request.getRequestDispatcher(resultado.getProximaPagina()).forward(request, response);

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doRequest(request, response);
	}

}
