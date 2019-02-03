package br.edu.ifpb.collegialis.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.edu.ifpb.collegialis.dao.AlunoDAO;
import br.edu.ifpb.collegialis.dao.AssuntoDAO;
import br.edu.ifpb.collegialis.dao.ColegiadoDAO;
import br.edu.ifpb.collegialis.dao.ManagedEMContext;
import br.edu.ifpb.collegialis.dao.PersistenceUtil;
import br.edu.ifpb.collegialis.dao.ProcessoDAO;
import br.edu.ifpb.collegialis.dao.ProfessorDAO;
import br.edu.ifpb.collegialis.dao.ReuniaoDAO;
import br.edu.ifpb.collegialis.dao.UsuarioDAO;
import br.edu.ifpb.collegialis.dao.VotoDAO;
import br.edu.ifpb.collegialis.entity.Aluno;
import br.edu.ifpb.collegialis.entity.Assunto;
import br.edu.ifpb.collegialis.entity.Colegiado;
import br.edu.ifpb.collegialis.entity.Processo;
import br.edu.ifpb.collegialis.entity.Professor;
import br.edu.ifpb.collegialis.entity.Reuniao;
import br.edu.ifpb.collegialis.entity.Usuario;
import br.edu.ifpb.collegialis.entity.Voto;
import br.edu.ifpb.collegialis.type.StatusReuniao;
import br.edu.ifpb.collegialis.type.TipoDecisao;
import br.edu.ifpb.collegialis.type.TipoVoto;

/**
 * É presico comentar a dependência "weld-se" do pom.xml antes de executar
 * estes testes. A dependência importa uma versão do log4j que é
 * incompatível com a necessária pelo JUnit. Depois de executados os testes,
 * retirar os comentários na dependência para evitar os erros de compilação
 * oriundos de classes que fazem uso dela.
 * 
 * @author fred
 *
 */

// DROP SCHEMA PUBLIC CASCADE

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InsereDadosBanco {

	private static EntityManagerFactory emf;

	private static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");

	private EntityManager em;

	@BeforeClass
	public static void init() {
		PersistenceUtil.createEntityManagerFactory("collegialis");
		emf = PersistenceUtil.getEntityManagerFactory();
		ManagedEMContext.bind(emf, emf.createEntityManager());
		System.out.println("init()");
	}

	@AfterClass
	public static void destroy() {
		if (emf != null) {
			emf.close();
			System.out.println("destroy()");
		}
	}

	@Before
	public void initEM() {
		em = emf.createEntityManager();
	}

	/**
	 * Insere Alunos
	 */
	@Test
	public void test01InsereAlunos() {
		try {
			System.out.println("testInsereAlunos");
			AlunoDAO dao = new AlunoDAO(em);
			dao.beginTransaction();
			Aluno a = new Aluno("2012041033", "123");
			a.setNome("Jose Carlos da Silva");
			a.setEmail("jcds@ifpb.edu.br");
			a.setFone("3422-9900");
			a.setMatricula("2012041033");

			dao.insert(a);

			a = new Aluno("2013041125", "123");
			a.setNome("Maria Clara dos Santos");
			a.setEmail("mcds@ifpb.edu.br");
			a.setFone("3662-5536");
			a.setMatricula("2013041125");

			dao.insert(a);
			a = new Aluno("2012041056", "123");
			a.setNome("Joao Firmino da Costa");
			a.setEmail("jfdc@ifpb.edu.br");
			a.setFone("3556-8433");
			a.setMatricula("2012041056");

			dao.insert(a);
			a = new Aluno("2013041012", "123");
			a.setNome("Priscila Almeida Pontes");
			a.setEmail("pap@ifpb.edu.br");
			a.setFone("3417-4237");
			a.setMatricula("2013041012");

			dao.insert(a);
			a = new Aluno("20130414512", "123");
			a.setNome("Walter Pontes Fontes");
			a.setEmail("wpf@ifpb.edu.br");
			a.setFone("3417-4645");
			a.setMatricula("20130414512");

			dao.insert(a);
			a = new Aluno("20130414564", "123");
			a.setNome("Amanda Correia Lima");
			a.setEmail("acl@ifpb.edu.br");
			a.setFone("9888-4099");
			a.setMatricula("20130414564");

			dao.insert(a);
			a = new Aluno("2013041474", "123");
			a.setNome("Rogerio Nunes");
			a.setEmail("rn@ifpb.edu.br");
			a.setFone("98388-4787");
			a.setMatricula("2013041474");

			dao.insert(a);
			a = new Aluno("2013041110", "123");
			a.setNome("Carol Soares Barbosa");
			a.setEmail("csb@ifpb.edu.br");
			a.setFone("98747-4849");
			a.setMatricula("2013041110");

			dao.insert(a);
			a = new Aluno("2013041556", "123");
			a.setNome("Cesar Ferreira da Silva");
			a.setEmail("cfds@ifpb.edu.br");
			a.setFone("98821-4899");
			a.setMatricula("2013041556");

			dao.insert(a);
			a = new Aluno("2013041498", "123");
			a.setNome("Natalia Seixas Gomes");
			a.setEmail("nsg@ifpb.edu.br");
			a.setFone("94432-0199");
			a.setMatricula("2013041498");

			dao.insert(a);
			dao.commit();
		} catch (Exception e) {
			Assert.fail("Erro de BD");
		}
	}

	/**
	 * Insere Assuntos
	 */
	@Test
	public void test02InsereAssuntos() {
		try {
			System.out.println("testInsereAssuntos");
			AssuntoDAO dao = new AssuntoDAO(em);
			dao.beginTransaction();
			Assunto a = new Assunto();
			a.setDescricao("Trancamento de matrícula");
			dao.insert(a);
			a = new Assunto();
			a.setDescricao("Reabertura de matrícula");
			dao.insert(a);
			a = new Assunto();
			a.setDescricao("Dispensa de pré-requisito");
			dao.insert(a);
			dao.commit();
		} catch (Exception e) {
			Assert.fail("Erro de BD");
		}
	}

	/**
	 * Insere professores
	 */
	@Test
	public void test04bInsereProfessores() {

		System.out.println("testInsereProfessores");
		ProfessorDAO pdao = new ProfessorDAO(em);
		pdao.beginTransaction();
		Professor p1 = new Professor("1200309", "123");
		p1.setNome("Frederico Costa");
		p1.setEmail("fred@ifpb.edu.br");
		p1.setMatricula("1200309");
		p1.setFone("988157517");
		p1.setCoordenador(true);

		pdao.insert(p1);

		Professor p2 = new Professor("1200310", "123");
		p2.setNome("Valeria Cavalcanti");
		p2.setEmail("valeria@ifpb.edu.br");
		p2.setMatricula("1200310");
		p2.setFone("988887766");

		pdao.insert(p2);

		Professor p3 = new Professor("1200311", "123");
		p3.setNome("Marcus Varandas");
		p3.setEmail("varandas@ifpb.edu.br");
		p3.setMatricula("1200311");
		p3.setFone("99328874");

		pdao.insert(p3);

		Professor p4 = new Professor("1200312", "123");
		p4.setNome("Cândido Egypto");
		p4.setEmail("candido@ifpb.edu.br");
		p4.setMatricula("1200312");
		p4.setFone("99322744");

		pdao.insert(p4);

		Professor p5 = new Professor("1200313", "123");
		p5.setNome("Thiago Gouveia");
		p5.setEmail("tgouveia@ifpb.edu.br");
		p5.setMatricula("1200313");
		p5.setFone("99347084");

		pdao.insert(p5);

		pdao.commit();
	}

	/**
	 * Insere membros do colegiado
	 */
	@Test
	public void test06InsereMembrosColegiado() {

		System.out.println("testInsereMemmbrosColegiado");
		try {
			ProfessorDAO pdao = new ProfessorDAO(em);
			Professor p1 = pdao.find(11);
			Professor p2 = pdao.find(12);
			Professor p3 = pdao.find(13);
			Professor p4 = pdao.find(14);
			Professor p5 = pdao.find(15);

			AlunoDAO adao = new AlunoDAO(em);
			Aluno a1 = adao.find(1);
			Aluno a2 = adao.find(2);

			ColegiadoDAO cdao = new ColegiadoDAO(em);
			cdao.beginTransaction();
			Colegiado cTSI = new Colegiado();
			cTSI.setDescricao("Colegiado I - 2014");
			cTSI.setDataInicio(fmt.parse("10/04/2014"));
			cTSI.setDataFim(fmt.parse("06/06/2016"));
			cTSI.setPortaria("DG-JP 122/2014");
			cTSI.setCurso("tsi");

			List<Professor> membrosTSI = new ArrayList<Professor>();
			membrosTSI.add(p1);
			membrosTSI.add(p2);
			membrosTSI.add(p3);

			cTSI.setMembros(membrosTSI);
			cTSI.setRepresentante(a1);

			cdao.insert(cTSI);

			Colegiado cRedes = new Colegiado();
			cRedes.setDescricao("Colegiado II - 2015");
			cRedes.setDataInicio(fmt.parse("22/02/2015"));
			cRedes.setDataFim(fmt.parse("06/06/2016"));
			cRedes.setPortaria("DG-JP 175/2015");
			cRedes.setCurso("redes");

			List<Professor> membrosRedes = new ArrayList<Professor>();

			// Redes
			membrosRedes.add(p4);
			membrosRedes.add(p5);
			membrosRedes.add(p1);
			cRedes.setMembros(membrosRedes);
			cRedes.setRepresentante(a2);
			cdao.insert(cRedes);

			cdao.commit();

		} catch (ParseException e) {
			Assert.fail("Erro na data");
		}
	}

	@Test
	public void test07InsereProcessos() {
		System.out.println("testInsereProcessos");
		try {

			AssuntoDAO adao = new AssuntoDAO(em);
			Assunto a1 = adao.find(1);
			Assunto a2 = adao.find(2);
			Assunto a3 = adao.find(3);

			AlunoDAO aldao = new AlunoDAO(em);
			Aluno al1 = aldao.find(1);
			Aluno al2 = aldao.find(2);
			Aluno al3 = aldao.find(3);
			Aluno al4 = aldao.find(4);
			Aluno al5 = aldao.find(5);
			Aluno al6 = aldao.find(6);
			Aluno al7 = aldao.find(7);
			Aluno al8 = aldao.find(8);
			Aluno al9 = aldao.find(9);
			Aluno al10 = aldao.find(10);

			ProfessorDAO profdao = new ProfessorDAO(em);
			Professor pr1 = profdao.find(1);
			Professor pr2 = profdao.find(2);
			Professor pr3 = profdao.find(3);
			Professor pr4 = profdao.find(4);
			Professor pr5 = profdao.find(5);

			ProcessoDAO pdao = new ProcessoDAO(em);
			pdao.beginTransaction();
			Processo p1 = new Processo();
			p1.setNumero("2300212445005/2016");
			p1.setAssunto(a1);
			p1.setDataRecepcao(fmt.parse("08/03/2016"));
			p1.setDataDistribuicao(fmt.parse("10/03/2016"));
			p1.setDataParecer(fmt.parse("17/03/2016"));
			p1.setDecisao(TipoDecisao.DEFERIDO);
			p1.setRelator(pr1);
			p1.setRequisitante(al1);
			pdao.insert(p1);

			Processo p2 = new Processo();
			p2.setNumero("2300277895031/2016");
			p2.setAssunto(a2);
			p2.setDataRecepcao(fmt.parse("04/03/2016"));
			p2.setDataDistribuicao(fmt.parse("11/06/2016"));
			p2.setDataParecer(fmt.parse("19/03/2016"));
			p2.setDecisao(TipoDecisao.INDEFERIDO);
			p2.setRelator(pr2);
			p2.setRequisitante(al2);
			pdao.insert(p2);

			Processo p3 = new Processo();
			p3.setNumero("2300335767567/2016");
			p3.setAssunto(a3);
			p3.setDataRecepcao(fmt.parse("07/03/2016"));
			p3.setDataDistribuicao(fmt.parse("11/03/2016"));
			p3.setDataParecer(fmt.parse("15/03/2016"));
			p3.setDecisao(TipoDecisao.DEFERIDO);
			p3.setRelator(pr3);
			p3.setRequisitante(al3);
			pdao.insert(p3);

			Processo p4 = new Processo();
			p4.setNumero("2300335353553/2016");
			p4.setAssunto(a2);
			p4.setDataRecepcao(fmt.parse("07/03/2016"));
			p4.setDataDistribuicao(fmt.parse("11/03/2016"));
			p4.setDataParecer(fmt.parse("14/03/2016"));
			p4.setDecisao(TipoDecisao.INDEFERIDO);
			p4.setRelator(pr1);
			p4.setRequisitante(al4);
			pdao.insert(p4);

			Processo p5 = new Processo();
			p5.setNumero("2300335833392/2016");
			p5.setAssunto(a3);
			p5.setDataRecepcao(fmt.parse("17/05/2016"));
			p5.setDataDistribuicao(fmt.parse("21/05/2016"));
			p5.setDataParecer(fmt.parse("23/05/2016"));
			p5.setDecisao(TipoDecisao.DEFERIDO);
			p5.setRelator(pr2);
			p5.setRequisitante(al5);
			pdao.insert(p5);

			Processo p6 = new Processo();
			p6.setNumero("2300334840948/2016");
			p6.setAssunto(a3);
			p6.setDataRecepcao(fmt.parse("18/05/2016"));
			p6.setDataDistribuicao(fmt.parse("21/05/2016"));
			p6.setDataParecer(fmt.parse("24/05/2016"));
			p6.setDecisao(TipoDecisao.DEFERIDO);
			p6.setRelator(pr3);
			p6.setRequisitante(al6);
			pdao.insert(p6);

			Processo p7 = new Processo();
			p7.setNumero("23003357675677/2016");
			p7.setAssunto(a1);
			p7.setDataRecepcao(fmt.parse("17/05/2016"));
			p7.setDataDistribuicao(fmt.parse("21/05/2016"));
			p7.setDataParecer(fmt.parse("25/05/2016"));
			p7.setDecisao(TipoDecisao.INDEFERIDO);
			p7.setRelator(pr1);
			p7.setRequisitante(al7);
			pdao.insert(p7);

			Processo p8 = new Processo();
			p8.setNumero("2300330650334/2016");
			p8.setAssunto(a2);
			p8.setDataRecepcao(fmt.parse("15/06/2016"));
			p8.setDataDistribuicao(fmt.parse("19/06/2016"));
			p8.setDecisao(TipoDecisao.AGUARDANDO);
			p8.setRelator(pr4);
			p8.setRequisitante(al8);
			pdao.insert(p8);

			Processo p9 = new Processo();
			p9.setNumero("2300331134367/2016");
			p9.setAssunto(a3);
			p9.setDataRecepcao(fmt.parse("16/06/2016"));
			p9.setDataDistribuicao(fmt.parse("19/06/2016"));
			p9.setDecisao(TipoDecisao.AGUARDANDO);
			p9.setRelator(pr5);
			p9.setRequisitante(al9);
			pdao.insert(p9);

			Processo p10 = new Processo();
			p10.setNumero("230033558381/2016");
			p10.setAssunto(a2);
			p10.setDataRecepcao(fmt.parse("19/06/2016"));
			p10.setDataDistribuicao(fmt.parse("20/06/2016"));
			p10.setDecisao(TipoDecisao.AGUARDANDO);
			p10.setRelator(pr1);
			p10.setRequisitante(al10);
			pdao.insert(p10);
			pdao.commit();

		} catch (ParseException e) {
			Assert.fail();
		}

	}

	@Test
	public void test08InsereReuniao() {
		System.out.println("testInsereReuniao");
		try {
			ColegiadoDAO coldao = new ColegiadoDAO(em);
			Colegiado colredes = coldao.find(1);
			Colegiado coltsi = coldao.find(2);

			ProcessoDAO pdao = new ProcessoDAO(em);
			List<Processo> procs1 = pdao.find(new Integer[] { 1, 2, 3, 4 });
			List<Processo> procs2 = pdao.find(new Integer[] { 5, 6, 7 });
			List<Processo> procs3 = pdao.find(new Integer[] { 8, 9, 10 });

			ReuniaoDAO rdao = new ReuniaoDAO(em);
			rdao.beginTransaction();
			Reuniao r1 = new Reuniao();
			r1.setDescricao("Reuniao_05/2016" );
			r1.setColegiado(coltsi);
			r1.setData(fmt.parse("21/03/2016"));
			r1.setStatus(StatusReuniao.ENCERRADA);
			r1.setProcessos(procs1);

			Reuniao r2 = new Reuniao();
			r2.setDescricao("Reuniao_06/2016");
			r2.setColegiado(coltsi);
			r2.setData(fmt.parse("27/05/2016"));
			r2.setStatus(StatusReuniao.ENCERRADA);
			r2.setProcessos(procs2);

			Reuniao r3 = new Reuniao();
			r3.setDescricao("Reuniao_07/2016" );
			r3.setColegiado(colredes);
			r3.setData(fmt.parse("27/06/2016"));
			r3.setStatus(StatusReuniao.PLANEJADA);
			r3.setProcessos(procs3);

			rdao.insert(r1);
			rdao.insert(r2);
			rdao.insert(r3);
			
			for (Processo p : procs1) 
				p.setReuniao(r1);
					
			for (Processo p : procs2) 
				p.setReuniao(r2);			
			
			for (Processo p : procs3) 
				p.setReuniao(r3);
					
			rdao.commit();

			
		} catch (ParseException e) {
			Assert.fail();
		}

	}

	@Test
	public void test09InsereVoto() {

		System.out.println("testInsereVoto");
		ProcessoDAO pdao = new ProcessoDAO(em);
		List<Processo> procs = pdao.find(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 });

		ProfessorDAO profdao = new ProfessorDAO(em);
		Professor pr1 = profdao.find(1);
		Professor pr2 = profdao.find(2);
		Professor pr3 = profdao.find(3);
		Professor pr4 = profdao.find(4);
		Professor pr5 = profdao.find(5);

		AlunoDAO adao = new AlunoDAO(em);
		Aluno a1 = adao.find(1);
		Aluno a2 = adao.find(2);

		VotoDAO vdao = new VotoDAO(em);
		Voto v1 = new Voto();
		v1.setVoto(TipoVoto.DEFERIDO);
		v1.setMembro(pr2);
		v1.setProcesso(procs.get(0));

		Voto v2 = new Voto();
		v2.setVoto(TipoVoto.DEFERIDO);
		v2.setMembro(pr3);
		v2.setProcesso(procs.get(0));

		Voto v3 = new Voto();
		v3.setVoto(TipoVoto.DEFERIDO);
		v3.setMembro(pr1);
		v3.setProcesso(procs.get(1));

		Voto v4 = new Voto();
		v4.setVoto(TipoVoto.INDEFERIDO);
		v4.setMembro(pr3);
		v4.setProcesso(procs.get(1));

		Voto v5 = new Voto();
		v5.setVoto(TipoVoto.DEFERIDO);
		v5.setMembro(pr1);
		v5.setProcesso(procs.get(2));

		Voto v6 = new Voto();
		v6.setVoto(TipoVoto.DEFERIDO);
		v6.setMembro(pr1);
		v6.setProcesso(procs.get(2));

		Voto v7 = new Voto();
		v7.setVoto(TipoVoto.INDEFERIDO);
		v7.setMembro(pr2);
		v7.setProcesso(procs.get(3));

		Voto v8 = new Voto();
		v8.setVoto(TipoVoto.DEFERIDO);
		v8.setMembro(pr3);
		v8.setProcesso(procs.get(3));

		Voto v9 = new Voto();
		v9.setVoto(TipoVoto.INDEFERIDO);
		v9.setMembro(pr1);
		v9.setProcesso(procs.get(4));

		Voto v10 = new Voto();
		v10.setVoto(TipoVoto.DEFERIDO);
		v10.setMembro(pr3);
		v10.setProcesso(procs.get(4));

		Voto v11 = new Voto();
		v11.setVoto(TipoVoto.INDEFERIDO);
		v11.setMembro(pr1);
		v11.setProcesso(procs.get(5));

		Voto v12 = new Voto();
		v12.setVoto(TipoVoto.DEFERIDO);
		v12.setMembro(pr2);
		v12.setProcesso(procs.get(5));

		Voto v13 = new Voto();
		v13.setVoto(TipoVoto.DEFERIDO);
		v13.setMembro(pr2);
		v13.setProcesso(procs.get(6));

		Voto v14 = new Voto();
		v14.setVoto(TipoVoto.DEFERIDO);
		v14.setMembro(pr3);
		v14.setProcesso(procs.get(6));

		Voto v15 = new Voto();
		v15.setVoto(TipoVoto.DEFERIDO);
		v15.setRepresentante(a1);
		v15.setProcesso(procs.get(7));

		Voto v16 = new Voto();
		v16.setVoto(TipoVoto.DEFERIDO);
		v16.setRepresentante(a2);
		v16.setProcesso(procs.get(7));

		Voto v17 = new Voto();
		v17.setVoto(TipoVoto.DEFERIDO);
		v17.setMembro(pr4);
		v17.setProcesso(procs.get(8));

		Voto v18 = new Voto();
		v18.setVoto(TipoVoto.DEFERIDO);
		v18.setMembro(pr1);
		v18.setProcesso(procs.get(8));

		Voto v19 = new Voto();
		v19.setVoto(TipoVoto.DEFERIDO);
		v19.setMembro(pr4);
		v19.setProcesso(procs.get(9));

		Voto v20 = new Voto();
		v20.setVoto(TipoVoto.DEFERIDO);
		v20.setMembro(pr5);
		v20.setProcesso(procs.get(9));

		vdao.beginTransaction();
		vdao.insert(v1);
		vdao.insert(v2);
		vdao.insert(v3);
		vdao.insert(v4);
		vdao.insert(v5);
		vdao.insert(v6);
		vdao.insert(v7);
		vdao.insert(v8);
		vdao.insert(v9);
		vdao.insert(v10);
		vdao.insert(v11);
		vdao.insert(v12);
		vdao.insert(v13);
		vdao.insert(v14);
		vdao.insert(v15);
		vdao.insert(v16);
		// vdao.insert(v17);
		// vdao.insert(v18);
		// vdao.insert(v19);
		// vdao.insert(v20);

		vdao.commit();
	}

}
