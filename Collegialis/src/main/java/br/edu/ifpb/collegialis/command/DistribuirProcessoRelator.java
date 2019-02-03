package br.edu.ifpb.collegialis.command;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifpb.collegialis.dao.AlunoDAO;
import br.edu.ifpb.collegialis.dao.AssuntoDAO;
import br.edu.ifpb.collegialis.dao.ProcessoDAO;
import br.edu.ifpb.collegialis.dao.ProfessorDAO;
import br.edu.ifpb.collegialis.dao.UsuarioDAO;
import br.edu.ifpb.collegialis.entity.Aluno;
import br.edu.ifpb.collegialis.entity.Assunto;
import br.edu.ifpb.collegialis.entity.Processo;
import br.edu.ifpb.collegialis.entity.Professor;
import br.edu.ifpb.collegialis.entity.Usuario;
import br.edu.ifpb.collegialis.type.TipoDecisao;
import br.edu.ifpb.collegialis.type.TipoStatusProcesso;

public class DistribuirProcessoRelator implements IComando {

	@Override
	public Resultado execute(HttpServletRequest request, HttpServletResponse response) {

		Resultado resultado = new Resultado();

		String action = request.getParameter("action");

		String idString = request.getParameter("id");
		int id = Integer.parseInt(idString);
		
		ProcessoDAO dao = new ProcessoDAO();
		Processo p = dao.find(id);
		
		UsuarioDAO userdao = new UsuarioDAO();
		List<Usuario> usuarios = userdao.findAll();
		List<Professor> professores = new ArrayList<Professor>();

		for (Usuario u : usuarios) {
			if (u instanceof Professor) {
				professores.add((Professor) u);
			}
		}
		request.setAttribute("professores", professores);

		if (action != null) {
			if (action.equals("distribuir")) {
				String profString = request.getParameter("professor");
				int idProf = Integer.parseInt(profString);

				String dataDistribuicao = request.getParameter("dataDistribuicao");
		
				SimpleDateFormat dataformatada = new SimpleDateFormat("yyyy-MM-dd");
				Date date = null;
				try {
					date = dataformatada.parse(dataDistribuicao);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}

				ProfessorDAO profDao = new ProfessorDAO();
				Professor relator = profDao.find(idProf);

				p.setDataDistribuicao(date);
				p.setRelator(relator);
				p.setStatus(TipoStatusProcesso.ABERTO);
				dao.beginTransaction();
				try {
					dao.update(p);
				} catch (Exception e) {
					resultado.setErro(true);
					resultado.setRedirect(false);
					resultado.addMensagem("Erro ao distribuir Processo!");
					resultado.setProximaPagina("distProcesso.jsp");
				}
				dao.commit();
				resultado.setErro(false);
				resultado.setRedirect(true);
				resultado.setProximaPagina("controller.do?op=listprocessos");
			}
		}else {
			request.setAttribute("processo", p);
			resultado.setErro(false);
			resultado.setRedirect(false);
			resultado.setProximaPagina("distProcesso.jsp");
		}

		return resultado;
	}
}