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
		<h1>Distibuir Processo ao Relator</h1>
		<div class="content">
			<c:if test="${not empty _erro}">
				<ul>
					<c:forEach var="erro" items="${_erro}">
						<li class="msgErro"><i class="fas fa-exclamation-triangle"></i>
							${erro}</li>
					</c:forEach>
				</ul>
			</c:if>
			<form action="controller.do?op=distpro" method="post">
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
						<label for="professor">Relator</label> <select name="professor"
							id="professor" class="form-control">
							<option value="">Selecione</option>
							<c:forEach var="professor" items="${professores}">
								<option value="${professor.id}">${professor.nome}</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group col-md-6">
						<label for="dataDistribuicao">Data da distribuição</label> <input
							type="date" class="form-control" id="dataDistribuicao"
							name="dataDistribuicao" />
					</div>
				</div>
				<div class="row">
					<div class="form-group col-md-6">
						<input type="hidden" value="distribuir" name="action" id="action" />
						<input type="hidden" value="${processo.id}" name="id" id="id" />
						<input type="submit" class="btn btn-success"
							value="Distribuir Processo" name="distPro" id="distPro" />
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>