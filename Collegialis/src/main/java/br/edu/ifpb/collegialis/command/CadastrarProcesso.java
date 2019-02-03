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
import br.edu.ifpb.collegialis.dao.UsuarioDAO;
import br.edu.ifpb.collegialis.entity.Aluno;
import br.edu.ifpb.collegialis.entity.Assunto;
import br.edu.ifpb.collegialis.entity.Processo;
import br.edu.ifpb.collegialis.entity.Professor;
import br.edu.ifpb.collegialis.entity.Usuario;
import br.edu.ifpb.collegialis.type.TipoDecisao;
import br.edu.ifpb.collegialis.type.TipoStatusProcesso;

public class CadastrarProcesso implements IComando {

	@Override
	public Resultado execute(HttpServletRequest request, HttpServletResponse response) {

		String action = request.getParameter("action");

		String idString = request.getParameter("id");

		Integer id = null;
		if (idString != null && !idString.isEmpty()) {
			id = Integer.parseInt(idString);
		}
		 //System.out.println(id);

		Resultado resultado = new Resultado();

		UsuarioDAO dao = new UsuarioDAO();
		List<Usuario> usuarios = dao.findAll();
		List<Aluno> alunos = new ArrayList<Aluno>();
		List<Professor> professores = new ArrayList<Professor>();

		for (Usuario u : usuarios) {
			if (u instanceof Professor) {
				professores.add((Professor) u);
			}
			if (u instanceof Aluno) {
				alunos.add((Aluno) u);
			}
		}
		request.setAttribute("alunos", alunos);
		request.setAttribute("professores", professores);

		AssuntoDAO daoAss = new AssuntoDAO();
		List<Assunto> assuntos = daoAss.findAll();
		request.setAttribute("assuntos", assuntos);

		ProcessoDAO daoPro = new ProcessoDAO();

		if (action != null) {
			if (action.equals("cadastrar")) {
				String aluno = request.getParameter("aluno");
				String dataRecepcao = request.getParameter("dataRecepcao");
				String Assunto = request.getParameter("assunto");

				int idAssunto = Integer.parseInt(Assunto);
				int idAluno = Integer.parseInt(aluno);
				String numero = request.getParameter("numero");
				SimpleDateFormat dataformatada = new SimpleDateFormat("yyyy-MM-dd");
				Date date = null;
				try {
					date = dataformatada.parse(dataRecepcao);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}

				Assunto a = daoAss.find(idAssunto);

				AlunoDAO daoalu = new AlunoDAO();
				Aluno requisitante = daoalu.find(idAluno);

				Processo p;
				daoPro.beginTransaction();
				//System.out.println(id);
				try {
					if (id == null) {
						p = new Processo();
						p.setDecisao(TipoDecisao.AGUARDANDO);
						p.setStatus(TipoStatusProcesso.ABERTO);
						p.setAssunto(a);
						p.setDataRecepcao(date);
						p.setNumero(numero);
						p.setRequisitante(requisitante);
						daoPro.insert(p);
					} else {
						p = daoPro.find(id);
						p.setAssunto(a);
						p.setDataRecepcao(date);
						p.setNumero(numero);
						p.setRequisitante(requisitante);
						daoPro.update(p);
					}
				} catch (Exception e) {
					resultado.setErro(true);
					resultado.setRedirect(false);
					resultado.addMensagem("Erro ao gravar Processo!");
					resultado.setProximaPagina("cadProcesso.jsp");
				}
				daoPro.commit();
				resultado.setErro(false);
				resultado.setRedirect(true);
				resultado.setProximaPagina("controller.do?op=listprocessos");
			}
			if (action.equals("editar")) {
				Processo p = daoPro.find(id);
				request.setAttribute("processo", p);
				resultado.setErro(false);
				resultado.setRedirect(false);
				resultado.setProximaPagina("cadProcesso.jsp");
			}

		} else {
			resultado.setErro(false);
			resultado.setRedirect(false);
			resultado.setProximaPagina("cadProcesso.jsp");
		}
		return resultado;
	}
}