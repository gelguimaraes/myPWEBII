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
<h1 class="title">Listagem de Colegiado</h1>
	<div class="content">
		<div class="table-responsive">
			
			<c:if test="${not empty listaColegiado}">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>ID</th>
							<th>Curso</th>
							<th>Descrição</th>
							<th>Representante</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="col" items="${listaColegiado}">
							<tr>
								<td>${col.id}</td>
								<td>${col.curso}</td>
								<td>${col.descricao}</td>
								<td>${col.representante.nome}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
		</div>
	</div>
</body>
</html>