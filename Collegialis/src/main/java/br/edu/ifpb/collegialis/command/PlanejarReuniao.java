package br.edu.ifpb.collegialis.command;




import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import br.edu.ifpb.collegialis.dao.ColegiadoDAO;
import br.edu.ifpb.collegialis.dao.ProcessoDAO;
import br.edu.ifpb.collegialis.dao.ReuniaoDAO;
import br.edu.ifpb.collegialis.entity.Colegiado;
import br.edu.ifpb.collegialis.entity.Processo;
import br.edu.ifpb.collegialis.entity.Reuniao;
import br.edu.ifpb.collegialis.type.StatusReuniao;
import br.edu.ifpb.collegialis.type.TipoStatusProcesso;

public class PlanejarReuniao  implements IComando {
	
	@Override
	public Resultado execute(HttpServletRequest request, HttpServletResponse response) {

		Resultado resultado = new Resultado();
		String action = request.getParameter("action");
		String curso = request.getParameter("curso");

		ProcessoDAO daopro = new ProcessoDAO();
		ReuniaoDAO daoReuniao = new ReuniaoDAO();
		ColegiadoDAO daocol = new ColegiadoDAO();
		List<Colegiado> colegiados = daocol.findAll();
		List<Processo> processosCurso = daopro.findProcessoCurso(curso);
		List<Processo> processos = new ArrayList<Processo>();
		HttpSession session = request.getSession();
		
		Colegiado col;
		
		if(!processosCurso.isEmpty()) {
			for(Processo  p : processosCurso) {
				if(p.getStatus() == TipoStatusProcesso.ABERTO) {
					processos.add(p);
				}
			}
		}
		
		
		@SuppressWarnings("unchecked")
		List<Processo> tempProcessos = (ArrayList<Processo>)session.getAttribute("processosAdd");
		Boolean add = true;
		
		if (action != null) {
			if (action.equals("planejar")) {
				
				String descricao = request.getParameter("descricao");
				String data = request.getParameter("data");
				
				int idCol = Integer.parseInt(request.getParameter("colegiado"));
				
				col = daocol.find(idCol);
				
				SimpleDateFormat dataformatada = new SimpleDateFormat("yyyy-MM-dd");
				Date date = null;
				try {
					date = dataformatada.parse(data);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				
				Reuniao r = new Reuniao();
				r.setColegiado(col);
				r.setData(date);
				r.setDescricao(descricao);
				r.setProcessos(tempProcessos);
				r.setStatus(StatusReuniao.PLANEJADA);
				
				
					
				
				daoReuniao.beginTransaction();
				try {
					daoReuniao.insert(r);
					for (Processo p : tempProcessos) {
						p.setReuniao(r);
						p.setStatus(TipoStatusProcesso.EMPAUTA);
						daopro.update(p);
					}
	
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				
				daoReuniao.commit();
				session.removeAttribute("processosAdd");
				resultado.setErro(false);
				resultado.setRedirect(false);
				resultado.setProximaPagina("controller.do?op=listreuniao");
				
			}else if (action.equals("addPro")) {
				String idPro = request.getParameter("processo");
				
				int id = Integer.parseInt(idPro);
				Processo p = daopro.find(id);
				
				if (tempProcessos == null) {
					tempProcessos = new ArrayList<>();
	
				}
				for(Processo pro : tempProcessos ) {
					if(pro == p) {
						add = false;
						break;
					}
				}
				if(add) {
					tempProcessos.add(p);	
				}
				
				request.setAttribute("processos", processos);
				request.setAttribute("colegiados", colegiados);
				request.setAttribute("selectedCurso", curso);
				session.setAttribute("processosAdd", tempProcessos);
				resultado.setErro(false);
				resultado.setRedirect(false);
				resultado.setProximaPagina("cadReuniao.jsp");
			}
			
			else if (action.equals("delPro")) {
				String idPro = request.getParameter("id");
				int id = Integer.parseInt(idPro);
				Processo p = daopro.find(id);
				
				for(Processo pro : tempProcessos ) {
					if(pro == p) {
						tempProcessos.remove(pro);
						break;
					}
				}
				request.setAttribute("processos", processos);
				request.setAttribute("colegiados", colegiados);
				session.setAttribute("processosAdd", tempProcessos);
				resultado.setErro(false);
				resultado.setRedirect(false);
				resultado.setProximaPagina("cadReuniao.jsp");
					
			}
			else if (action.equals("selectPro")) {
				
				request.setAttribute("processos", processos);
				request.setAttribute("colegiados", colegiados);
				request.setAttribute("selectedCurso", curso);
				session.setAttribute("processosAdd", tempProcessos);
				resultado.setErro(false);
				resultado.setRedirect(false);
				resultado.setProximaPagina("cadReuniao.jsp");
			}
			
		}else {
			request.setAttribute("processos", processos);
			request.setAttribute("colegiados", colegiados);
			resultado.setErro(false);
			resultado.setRedirect(false);
			resultado.setProximaPagina("cadReuniao.jsp");
		}
		


		return resultado;

	}

}
