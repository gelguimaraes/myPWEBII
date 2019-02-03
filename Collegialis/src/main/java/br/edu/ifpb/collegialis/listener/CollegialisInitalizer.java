package br.edu.ifpb.collegialis.listener;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import br.edu.ifpb.collegialis.dao.PersistenceUtil;


/**
 * Application Lifecycle Listener implementation class Pratica9Initalizer
 *
 */
@WebListener
public class CollegialisInitalizer implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
	}

	public void contextInitialized(ServletContextEvent sce) {
		PersistenceUtil.createEntityManagerFactory("collegialis");
		Properties p = new Properties();
		try {
			p.load(sce.getServletContext().getResourceAsStream("/WEB-INF/comandos.properties"));
			sce.getServletContext().setAttribute("comandos", p);
			System.out.println("Arquivo de comandos carregado!");
		} catch (IOException e1) {
			System.out.println("Erro ao carregar arquivo de comandos!");
		}
	}

}
