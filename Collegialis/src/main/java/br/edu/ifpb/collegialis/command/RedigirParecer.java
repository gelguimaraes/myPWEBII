package br.edu.ifpb.collegialis.command;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifpb.collegialis.dao.ProcessoDAO;
import br.edu.ifpb.collegialis.dao.VotoDAO;
import br.edu.ifpb.collegialis.entity.Processo;
import br.edu.ifpb.collegialis.entity.Voto;
import br.edu.ifpb.collegialis.type.TipoVoto;

public class RedigirParecer implements IComando {

	@Override
	public Resultado execute(HttpServletRequest request, HttpServletResponse response) {

		Resultado resultado = new Resultado();

		String action = request.getParameter("action");

		String idString = request.getParameter("id");
		int id = Integer.parseInt(idString);
		
		String votoString = request.getParameter("voto");
		
		
		
		ProcessoDAO dao = new ProcessoDAO();
		Processo p = dao.find(id);
		VotoDAO daovoto = new VotoDAO();
		

		if (action != null) {
			if (action.equals("redigir")) {
				
				String dataParecer = request.getParameter("dataParecer");
				String parecer = request.getParameter("parecer");

				SimpleDateFormat dataformatada = new SimpleDateFormat("yyyy-MM-dd");
				Date date = null;
				try {
					date = dataformatada.parse(dataParecer);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}

				Voto v = new Voto();
				
				if(votoString.equals("D")) {
					v.setVoto(TipoVoto.DEFERIDO);
				}else if(votoString.equals("I")) {
					v.setVoto(TipoVoto.INDEFERIDO);
				}
				
				v.setMembro(p.getRelator());
				v.setProcesso(p);
				
				daovoto.beginTransaction();
				daovoto.insert(v);
				daovoto.commit();
				
				Set<Voto> votos = new HashSet<>();
				votos.add(v);
			
				p.setVotos(votos);	
				p.setDataParecer(date);
				//System.out.println(date);
				p.setParecer(parecer);
				dao.beginTransaction();
				try {
					dao.update(p);
				} catch (Exception e) {
					resultado.setErro(true);
					resultado.setRedirect(false);
					resultado.addMensagem("Erro ao redigir Parecer!");
					resultado.setProximaPagina("redigirParecer.jsp");
				}
				dao.commit();
				resultado.setErro(false);
				resultado.setRedirect(false);
				resultado.setProximaPagina("controller.do?op=listprocessos");
			}
		}else {
			request.setAttribute("processo", p);
			if(p.getVotos() != null) {
				for(Voto v : p.getVotos()) {
					if(v.getMembro() == p.getRelator()) {
						if(v.getVoto() == (TipoVoto.DEFERIDO)) {
							request.setAttribute("deferido", "checked");
							break;
						}else if(v.getVoto() == TipoVoto.INDEFERIDO) {
							request.setAttribute("indeferido", "checked");
							break;
						}
					}
				}
			}else {
				request.setAttribute("indeferido", "");
				request.setAttribute("deferido", "");
			}
			
			resultado.setErro(false);
			resultado.setRedirect(false);
			resultado.setProximaPagina("redigirParecer.jsp");
		}

		return resultado;
	}
}