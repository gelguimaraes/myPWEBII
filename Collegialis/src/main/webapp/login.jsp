<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<%@ include file="header.jsp"%>
<body class="loginbody">
	<img src="images/logo-menu.png">
	<form
		action="${pageContext.request.contextPath}/controller.do?op=autentica"
		method="post" id="loginform" name="loginform">
		<div class="login">
			<c:if test="${not empty _erro}">
				<ul>
					<c:forEach var="erro" items="${_erro}">
						<li class="msgErro"><i class="fas fa-exclamation-triangle"></i>
							${erro}</li>
					</c:forEach>
				</ul>
			</c:if>
			<h3>Sistema de Gerenciameno de Colegiado</h3>
			<div class="form-group">
				<label for="login">Usuario</label> <input type="text"
					class="form-control" id="login" name="login" autofocus>
			</div>
			<div class="form-group">
				<label for="senha">Senha:</label> <input type="password"
					class="form-control" id="senha" name="senha">
			</div>
			<div class="form-button">
				<input type="submit" class="btn btn-success" value="Entrar">
			</div>
		</div>
	</form>
	<div class="rodape">
		<div class="container text-muted text-center">IFPB João Pessoa -
			Collegialis</div>
	</div>
</body>
</html>