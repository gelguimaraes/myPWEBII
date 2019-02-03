package br.edu.ifpb.collegialis.filter;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = { "/home.jsp" })
public class Session implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpSession session = ((HttpServletRequest) request).getSession();
		String usuario = (String) session.getAttribute("logado");

		if (usuario == null) {
			request.setAttribute("classErro", "erro naoLogado");
			request.setAttribute("msg", "Você não está logado no sistema!");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			// session.setAttribute("msg","Você não está logado no sistema!");
			// ((HttpServletResponse) response).sendRedirect("login.jsp");
		} 
		else {
			chain.doFilter(request, response);
		}
	}

}
