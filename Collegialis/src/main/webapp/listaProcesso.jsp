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
	<h1>Listando Processos</h1>

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
			<c:if test="${not empty listaProcesso}">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>ID</th>
							<th>Número</th>
							<th>Requisitante</th>
							<th>Assunto</th>
							<th>Relator</th>
							<th>Status</th>
							<th>Decisao</th>
							<th>Distribuir</th>
							<th>Editar</th>
							<th>Parecer</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="pro" items="${listaProcesso}">
							<tr>
								<td>${pro.id}</td>
								<td>${pro.numero}</td>
								<td>${pro.requisitante.nome}</td>
								<td>${pro.assunto.descricao}</td>
								<td>${pro.relator.nome}</td>
								<td>${pro.status}</td>
								<td>${pro.decisao}</td>
								<td style="text-align: center;"><a
									href="controller.do?op=distpro&id=${pro.id}"><i
										class="fas fa-user-check"></i></a></td>
								<td style="text-align: center;"><a
									href="controller.do?op=cadpro&action=editar&id=${pro.id}"><i
										class="fas fa-edit"></i></a></td>
								<td style="text-align: center;"><c:if
										test="${empty pro.parecer and not empty pro.relator}">
										<a href="controller.do?op=redpro&id=${pro.id}"><i
											class="fas fa-user-edit"></i></a>
									</c:if> <c:if test="${empty pro.parecer and  empty pro.relator}"></c:if>
									<c:if test="${not empty pro.parecer}">
										<a href="controller.do?op=redpro&id=${pro.id}"> <i
											class="fas fa-check-circle"></i></a>
									</c:if></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
		</div>
	</div>
</body>
</html>