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
		<h1>Cadastrar Processo</h1>
		<div class="content">
			<c:if test="${not empty _erro}">
				<ul>
					<c:forEach var="erro" items="${_erro}">
						<li class="msgErro"><i class="fas fa-exclamation-triangle"></i>
							${erro}</li>
					</c:forEach>
				</ul>
			</c:if>
			<form action="controller.do?op=cadpro" method="post">
				<div class="row">
					<div class="form-group col-md-6">
						<label for="aluno">Aluno</label> <select name="aluno" id="aluno"
							class="form-control">
							<option value="">Selecione</option>
							<c:forEach var="aluno" items="${alunos}">
								<c:choose>
									<c:when test="${aluno.id==processo.requisitante.id}">
										<option selected="selected" value="${aluno.id}">${aluno.nome}</option>
									</c:when>
									<c:otherwise>
										<option  value="${aluno.id}">${aluno.nome}</option>
									</c:otherwise>
								</c:choose>

							</c:forEach>
						</select>
					</div>
					<div class="form-group col-md-6">
						<label for="numero">Numero </label> <input type="text"
							class="form-control" id="numero" name="numero"
							value="${processo.numero}" />
					</div>
				</div>
				<div class="row">
					<div class="form-group col-md-6">
						<label for="assunto">Assunto</label> <select name="assunto"
							id="assunto" class="form-control">
							<option value="">Selecione</option>
							<c:forEach var="assunto" items="${assuntos}">
							<c:choose>
									<c:when test="${assunto.id==processo.assunto.id}">
										<option selected="selected" value="${assunto.id}">${assunto.descricao}</option>
									</c:when>
									<c:otherwise>
										<option  value="${assunto.id}">${assunto.descricao}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</div>
					<div class="form-group col-md-6">
					<fmt:formatDate var="dataRecepcao" pattern="yyyy-MM-dd" value="${processo.dataRecepcao}" />
						<label for="dataRecepcao">Data da Recepção</label> <input
							type="date" class="form-control" id="dataRecepcao"
							name="dataRecepcao" value="${dataRecepcao}"  />
					</div>
				</div>
				<div class="row">
					<div class="form-group col-md-3">
						<input type="hidden" value="cadastrar" name="action" id="action" />
						<input type="hidden" value="${processo.id}" name="id" id="id" />
						<input type="submit" class="btn btn-success"
							value="Cadastrar Processo" name="cadPro" id="cadPro" />
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>