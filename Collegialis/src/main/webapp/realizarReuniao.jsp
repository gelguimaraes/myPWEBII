<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<%@ include file="header.jsp"%>
<%@ include file="menu.jsp"%>
<body>
	<div class="container cont-height">
		<h1>Abrir Reunião</h1>
		<div class="content">
			<c:if test="${not empty _erro}">
				<ul>
					<c:forEach var="erro" items="${_erro}">
						<li class="msgErro"><i class="fas fa-exclamation-triangle"></i>
							${erro}</li>
					</c:forEach>
				</ul>
			</c:if>
			<form action="controller.do?op=realizareuniao" method="post">
				<input type="hidden" value="${reuniao.id}" name="id" id="id" /> <input
					type="hidden" value="${processo.id}" name="pro" id="pro" />
				<div class="form-group">
					<div class="col-md-12">
						<h3 class="form-group">
							Reunião ${reuniao.id}
							<fmt:formatDate var="data" pattern="dd/MM/yyyy"
								value="${reuniao.data}" />
							<span class="form-group pull-right">Data: ${data}</span>
						</h3>
						<span class="clearfix"></span>
						<hr>

					</div>
					<div class="col-md-4 ">
						<c:if test="${not empty processos}">
							<div class="panel panel-success">
								<div class="panel-heading">Processos em Pauta</div>
								<div class="panel-body">
									<c:forEach var="processo" items="${processos}">
										<p><a href="controller.do?op=realizareuniao&action=abrir&id=${reuniao.id}&pro=${processo.id}">${processo.numero}</a></p>
									</c:forEach>
								</div>
							</div>
						</c:if>
						<c:if test="${not empty retirados}">
							<div class="panel panel-success">
								<div class="panel-heading">Processos Retirados</div>
								<div class="panel-body">
									<c:forEach var="processo" items="${retirados}">
										<p>${processo.numero}</p>
									</c:forEach>
								</div>
							</div>
						</c:if>
						<c:if test="${not empty julgados}">
							<div class="panel panel-success">
								<div class="panel-heading">Processos Julgados</div>
								<div class="panel-body">
									<c:forEach var="processo" items="${julgados}">
										<p>${processo.numero} (${processo.decisao})</p>
									</c:forEach>
								</div>
							</div>
						</c:if>
					</div>
					<div class="col-md-8">
						<div class=" panel panel-success">
							<c:if test="${not empty processos}">
								<div class="panel-heading">Processo em Apreciação (Curso:
									${curso})</div>
								<div class="panel-body">
									<div class="row">
										<div class="form-group col-md-6">
											<p>Número do Processo: ${processo.numero}</p>
										</div>
										<div class="form-group col-md-6">
											<p>Requisitante: ${processo.requisitante.nome}</p>
										</div>
									</div>
									<div class="row">
										<div class="form-group col-md-6">
											<p>Assunto: ${processo.assunto.descricao}</p>
										</div>
										<div class="form-group col-md-6">
											<p>Relator: ${processo.relator.nome}</p>
										</div>
									</div>
									<div class="row">
										<div class="form-group col-md-12">
											<p>Decisão do Relator: ${decisaoRelator}</p>
											<input type="hidden" name="votoRel" value="${decisaoRelator}">
										</div>
									</div>
									<c:if test="${not empty membros}">
										<div class="row">
											<div class="form-group col-md-12">
												<h3>Votos</h3>
												<hr>
												<c:forEach var="membro" items="${membros}">
													<span>${membro.nome} </span>
													<input type="radio" name="votoM-${membro.id}"
														value="D-${membro.id}" />
													<label>Deferido</label>
													<input type="radio" name="votoM-${membro.id}"
														value="I-${membro.id}" />
													<label>Indeferido</label>
													<input type="radio" name="votoM-${membro.id}"
														value="A-${membro.id}" />
													<label>Ausente</label>
													<br>
												</c:forEach>
											</div>
										</div>
									</c:if>
									<c:if test="${not empty representante}">
										<div class="row">
											<div class="form-group col-md-12">
												<span>${representante.nome} </span> <input type="radio"
													name="votoR" value="D" /> <label>Deferido</label> <input
													type="radio" name="votoR" value="I" /> <label>Indeferido</label>
												<input type="radio" name="votoR" value="A" /> <label>Ausente</label>
												<br>
											</div>
										</div>
									</c:if>

									<div class="row">
										<div class="form-group col-md-3">
											<input type="submit" class="btn btn-success"
												value="Concluir Votação" name="concluir" id="concluir"
												formaction="controller.do?op=realizareuniao&action=concluir" />
										</div>
										<div class="form-group col-md-3">
											<input type="submit" class="btn btn-warning"
												value="Retirar de Pauta" name="retirar" id="retirar"
												formaction="controller.do?op=realizareuniao&action=retirar" />
										</div>
									</div>
								</div>
							</c:if>
							<c:if test="${empty processos}">
								<div class="text-center">
									<h4>Todos os processos foram juldados ou retirados de
										Pauta</h4>
								</div>
							</c:if>
						</div>
					</div>
					<div class="clearfix"></div>
				</div>

				<div class="row">
					<div class="form-group col-md-3">
						<input type="submit" class="btn btn-danger"
							value="Encerrar Reunião" name="encerrar" id="encerrar"
							formaction="controller.do?op=realizareuniao&action=encerrar" />
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>