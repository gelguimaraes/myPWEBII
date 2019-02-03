<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<%@ include file="header.jsp" %>
<%@ include file="menu.jsp" %>
<body>
	<div class="container cont-height">
		<h1>Redigir Parecer</h1>
		<div class="content">
			<c:if test="${not empty _erro}">
				<ul>
					<c:forEach var="erro" items="${_erro}">
						<li class="msgErro"><i class="fas fa-exclamation-triangle"></i>
							${erro}</li>
					</c:forEach>
				</ul>
			</c:if>
			<form action="controller.do?op=redpro" method="post">
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
					<label for="parecer">Parecer</label> 
					<textarea name="parecer" id="parecer" rows="9" cols="20" class="form-control">${processo.parecer}</textarea>
				</div>
				<div class="form-group col-md-6">
					<label for="assunto">Assunto</label> 
					<p>${processo.assunto.descricao}</p>
				</div>
				<div class="form-group col-md-6">
					<fmt:formatDate var="data" pattern="yyyy-MM-dd" value="${processo.dataParecer}" />
					<label for="dataDistribuicao">Data do Parecer</label> 
					<input type="date" value="${data}" class="form-control" id="dataParecer" name="dataParecer" />
				</div>
				<div class="form-group col-md-6">
					<label for="voto">Voto</label> <br>
					<input type="radio" value="D" ${deferido}  class="form-group" id="votod" name="voto" />  DEFERIDO 
					<input type="radio" value="I" ${indeferido} id="votoi" class="form-group" name="voto" /> INDEFERIDO
				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-6">
					<input type="hidden" value="redigir" name="action" id="action" />
					<input type="hidden" value="${processo.id}" name="id" id="id" />
					<input type="submit" class="btn btn-success"
						value="Redigir Parecer" name="redigirParecer" id="redigirParecer" />
				</div>
			</div>
			</form>
		</div>
	</div>
</body>
</html>