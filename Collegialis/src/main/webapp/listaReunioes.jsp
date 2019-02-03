<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<%@ include file="header.jsp" %>
<%@ include file="menu.jsp" %>
<body>
<h1>Listando Reuniões</h1>
	<div class="content">
		<c:if test="${not empty _erro}">
			<ul>
				<c:forEach var="erro" items="${_erro}">
					<li class="msgErro"><i class="fas fa-exclamation-triangle"></i>
						${erro}</li>
				</c:forEach>
			</ul>
		</c:if>
		<div class="table-responsive">
			<c:if test="${not empty listaReuniao}">
			
				<table class="table table-striped">
					<thead>
						<tr>
							<th>ID</th>
							<th>Descrição</th>
							<th>Data</th>
							<th>Processos</th>
							<th>Status</th>
							<th>Curso</th>
							<th>Abrir</th>
							<th>Excluir</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="reuniao" items="${listaReuniao}">
						<fmt:formatDate var="data" pattern="dd/MM/yyyy" value="${reuniao.data}" />
							<tr>
								<td>${reuniao.id}</td>
								<td>${reuniao.descricao}</td>
								<td>${data}</td>
								<td>${fn:length(reuniao.processos)}</td>
								<td>${reuniao.status}</td>
								<td>${reuniao.colegiado.curso}</td>
								<td>
								<c:if test="${reuniao.status == 'PLANEJADA'}">
								<a href="controller.do?op=realizareuniao&action=abrir&id=${reuniao.id}"><i
										class="fas fa-book-open"></i></a>
								</c:if>
								</td>
								
								<td>
								<a href="controller.do?op=realizareuniao&action=deletar&id=${reuniao.id}">
								<i class="fas fa-trash"></i></a>
								</td>
								
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
		</div>
	</div>
</body>
</html>