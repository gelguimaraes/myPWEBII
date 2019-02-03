package br.edu.ifpb.collegialis.command;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.ifpb.collegialis.dao.AlunoDAO;
import br.edu.ifpb.collegialis.dao.ProcessoDAO;
import br.edu.ifpb.collegialis.dao.ProfessorDAO;
import br.edu.ifpb.collegialis.dao.ReuniaoDAO;
import br.edu.ifpb.collegialis.dao.VotoDAO;
import br.edu.ifpb.collegialis.entity.Aluno;
import br.edu.ifpb.collegialis.entity.Processo;
import br.edu.ifpb.collegialis.entity.Professor;
import br.edu.ifpb.collegialis.entity.Reuniao;
import br.edu.ifpb.collegialis.entity.Voto;
import br.edu.ifpb.collegialis.type.StatusReuniao;
import br.edu.ifpb.collegialis.type.TipoDecisao;
import br.edu.ifpb.collegialis.type.TipoStatusProcesso;
import br.edu.ifpb.collegialis.type.TipoVoto;

public class AbrirReuniao implements IComando {

	@Override
	public Resultado execute(HttpServletRequest request, HttpServletResponse response) {

		Resultado resultado = new Resultado();
		String action = request.getParameter("action");
		String idString = request.getParameter("id");
		String proString = request.getParameter("pro");

		Integer id = null;
		Integer pro = null;

		if (idString != null && !idString.isEmpty()) {
			id = Integer.parseInt(idString);
		}

		if (proString != null && !proString.isEmpty()) {
			pro = Integer.parseInt(proString);
		}

		ReuniaoDAO daoReuniao = new ReuniaoDAO();
		ProcessoDAO daoPro = new ProcessoDAO();
		ProfessorDAO daoProf = new ProfessorDAO();
		AlunoDAO daoAlu = new AlunoDAO();
		VotoDAO daoVoto = new VotoDAO();

		Reuniao r = daoReuniao.find(id);
		String curso = r.getColegiado().getCurso();

		List<Professor> membros = daoProf.findDoCurso(curso);
		Aluno representante = daoAlu.findRepresCurso(curso);

		List<Processo> processosEmPauta = new ArrayList<>();
		List<Processo> processosRetirados = new ArrayList<>();
		List<Processo> processosJulgados = new ArrayList<>();

		if (!r.getProcessos().isEmpty()) {
			for (Processo p : r.getProcessos()) {
				if (p.getStatus() == TipoStatusProcesso.EMPAUTA) {
					processosEmPauta.add(p);
				} else if (p.getStatus() == TipoStatusProcesso.RETIRADO) {
					processosRetirados.add(p);
				} else if (p.getStatus() == TipoStatusProcesso.JULGADO) {
					processosJulgados.add(p);
				}

			}

			if (pro == null) {
				if (!processosEmPauta.isEmpty())
					pro = processosEmPauta.get(0).getId();
			}
		}

		if (action != null) {
			if (action.equals("abrir")) {
				if (pro != null) {
					Processo p = daoPro.find(pro);
					if (!(p.getVotos().isEmpty())) {
						for (Voto v : p.getVotos()) {
							if (v.getMembro() == p.getRelator()) {
								if (v.getVoto() == (TipoVoto.DEFERIDO)) {
									request.setAttribute("decisaoRelator", "DEFERIDO");
									break;
								} else if (v.getVoto() == TipoVoto.INDEFERIDO) {
									request.setAttribute("decisaoRelator", "INDEFERIDO");
									break;
								}
							}
						}
					}
					request.setAttribute("processo", p);
					request.setAttribute("representante", representante);
					request.setAttribute("membros", membros);
					request.setAttribute("curso", curso);
				}
				
				request.setAttribute("processos", processosEmPauta);
				request.setAttribute("julgados", processosJulgados);
				request.setAttribute("retirados", processosRetirados);
				request.setAttribute("reuniao", r);
				resultado.setErro(false);
				resultado.setRedirect(false);
				resultado.setProximaPagina("realizarReuniao.jsp");

			} else if (action.equals("encerrar")) {
				daoReuniao.beginTransaction();
				Boolean finalizar = true;
				try {
					for (Processo proc : r.getProcessos()) {
						//System.out.println(proc.getStatus());
						if (proc.getStatus() == TipoStatusProcesso.EMPAUTA) {
							finalizar = false;
							break;
						}
					}
					if (finalizar) {
						r.setStatus(StatusReuniao.ENCERRADA);
						daoReuniao.update(r);
						resultado.setErro(false);
					} else {
						resultado.addMensagem("Não pode encerrar a reunião, pois existem processos em pauta");
						resultado.setErro(true);
						resultado.setRedirect(true);
						resultado.setProximaPagina("listaReunioes.jsp");
						daoReuniao.rollback();
						return resultado;
						
					}

				} catch (Exception e) {
					System.out.println(e.getMessage());
					daoReuniao.rollback();
				}
				daoReuniao.commit();
				resultado.setRedirect(false);
				resultado.setProximaPagina("controller.do?op=listreuniao");

			} else if (action.equals("retirar")) {
				Processo p = daoPro.find(pro);
				daoPro.beginTransaction();
				try {
					p.setStatus(TipoStatusProcesso.RETIRADO);
					daoPro.update(p);
				} catch (Exception e) {
					daoPro.rollback();
					System.out.println(e.getMessage());
				}

				daoPro.commit();
				resultado.setProximaPagina("controller.do?op=realizareuniao&action=abrir&id=" + r.getId());

			} else if (action.equals("concluir")) {

				Map<String, String> votosM = new HashMap<>();
				String vototemp;
				Enumeration<String> enumera = request.getParameterNames();
				for (; enumera.hasMoreElements();) {
					String nomeParam = (String) enumera.nextElement();
					vototemp = nomeParam.split("-")[0];
					if (vototemp.equals("votoM")) {
						votosM.put(nomeParam, request.getParameter(nomeParam));
						// System.out.println(nomeParam +" = "+ request.getParameter(nomeParam));
					}

				}

				String votoR = request.getParameter("votoR");
				String votoRel = request.getParameter("votoRel");
				Processo p = daoPro.find(pro);

				int d = 0;
				int i = 0;
				int idM;
				Professor membro;
				Voto v;
				daoVoto.beginTransaction();
				try {
					for (String voto : votosM.values()) {
						v = new Voto();
						vototemp = voto.split("-")[0];
						idM = Integer.parseInt(voto.split("-")[1]);
						membro = daoProf.find(idM);
						v.setMembro(membro);
						v.setProcesso(p);
						if (vototemp.equals("D")) {
							d++;
							v.setVoto(TipoVoto.DEFERIDO);
						} else if (vototemp.equals("I")) {
							i++;
							v.setVoto(TipoVoto.INDEFERIDO);
						} else if (vototemp.equals("A")) {
							v.setAusente(true);
						}
						daoVoto.insert(v);
					}

					v = new Voto();
					v.setRepresentante(representante);
					v.setProcesso(p);
					if (votoR.equals("D")) {
						d++;
						v.setVoto(TipoVoto.DEFERIDO);
					} else if (votoR.equals("I")) {
						i++;
						v.setVoto(TipoVoto.INDEFERIDO);
					} else if (votoR.equals("A")) {
						v.setAusente(true);
					}
					daoVoto.insert(v);

				} catch (Exception e) {
					daoVoto.rollback();
					System.out.println(e.getMessage());
				}
				daoVoto.commit();

				if (votoRel.equals("DEFERIDO")) {
					d++;
				} else if (votoRel.equals("INDEFERIDO")) {
					i++;
				}

				//System.out.println("D:" + d + " I: " + i);
				daoPro.beginTransaction();
				try {
					if (d > i) {
						p.setDecisao(TipoDecisao.DEFERIDO);
					} else if (d < i) {
						p.setDecisao(TipoDecisao.INDEFERIDO);
					} else if (i == d) {
						p.setDecisao(
								(votoR.equals(TipoDecisao.DEFERIDO)) ? TipoDecisao.DEFERIDO : TipoDecisao.INDEFERIDO);
					}
					p.setStatus(TipoStatusProcesso.JULGADO);
					daoPro.update(p);
				} catch (Exception e) {
					daoPro.rollback();
					System.err.println(e.getMessage());
				}
				
				daoPro.commit();
				resultado.setRedirect(false);
				resultado.setProximaPagina("controller.do?op=realizareuniao&action=abrir&id=" + r.getId());

			} else if (action.equals("deletar")) {

				daoReuniao.beginTransaction();
				Boolean del = true;
				try {
					if (!r.getProcessos().isEmpty())
						for (Processo proc : r.getProcessos()) {
							if (proc.getStatus() == TipoStatusProcesso.EMPAUTA) {
								del = false;
								break;
							}
						}
					if (del) {
						daoReuniao.delete(r);
						resultado.setErro(false);
					} else {
						resultado.addMensagem("Não pode excluir, pois exitem processos em pauta");
						resultado.setErro(true);
					}

				} catch (Exception e) {
					daoReuniao.rollback();
					System.out.println(e.getMessage());
				}
				daoReuniao.commit();
				resultado.setRedirect(false);
				resultado.setProximaPagina("controller.do?op=listreuniao");
			}

		}
		return resultado;
	}

}
