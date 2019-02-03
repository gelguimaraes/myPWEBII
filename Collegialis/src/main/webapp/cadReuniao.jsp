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
		<h1>Planejar Reunião</h1>
		<div class="content">
			<c:if test="${not empty _erro}">
				<ul>
					<c:forEach var="erro" items="${_erro}">
						<li class="msgErro"><i class="fas fa-exclamation-triangle"></i>
							${erro}</li>
					</c:forEach>
				</ul>
			</c:if>
			<form action="controller.do?op=cadreuniao&action=planejar" method="post">
				<div class="row">
					<div class="form-group col-md-2">
						<label for="data">Data</label> <input type="date"
							class="form-control" id="data" name="data" value="${data}" />
					</div>
					<div class="form-group col-md-3">
						<label for="descricao">Descrição </label> <input type="text"
							class="form-control" id="descricao" name="descricao" />
					</div>
					<div class="form-group col-md-2">
						<label for="colegiado">Colegiado</label> 
						<select name="colegiado" id="colegiado" class="form-control" >
							<option value="">Selecione</option>
							<c:forEach var="col" items="${colegiados}">
								<option value="${col.id}" ${col.curso == selectedCurso ? 'selected="selected"' : ''} >${col.curso}</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group col-md-5">
						<label for="processo">Processo</label>
						<div class="form-inline">
							<select name="processo" id="processo" class="form-control">
								<option value="">Selecione</option>
								<c:forEach var="processo" items="${processos}">
									<option value="${processo.id}">${processo.numero}, ${processo.requisitante.nome}</option>
								</c:forEach>
							</select> 
							<input type="button" class="btn btn-success" value="Adicionar" name="addPro" id="addPro" />
						</div>
					</div>
				</div>

				<div class="row">
					<div class="table-responsive">
						<c:if test="${not empty processosAdd}">
							<table class="table table-striped">
								<thead>
									<tr>
										<th>ID</th>
										<th>Número</th>
										<th>Requisitante</th>
										<th>Assunto</th>
										<th>Relator</th>
										<th>Excluir</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="pro" items="${processosAdd}">
										<tr>
											<td>${pro.id}</td>
											<td>${pro.numero}</td>
											<td>${pro.requisitante.nome}</td>
											<td>${pro.assunto.descricao}</td>
											<td>${pro.relator.nome}</td>
											<td style="text-align: center;"><a
												href="controller.do?op=cadreuniao&action=delPro&id=${pro.id}"><i
													class="fas fa-trash-alt"></i></a></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</c:if>
					</div>

				</div>
				<div class="row">
					<div class="form-group col-md-3">
						<input type="hidden" value="${reuniao.id}" name="id" id="id" /> <input
							type="submit" class="btn btn-success" value="Planejar Reuniao"
							name="cadPro" id="cadPro" />
					</div>
				</div>
			</form>
		</div>
	</div>
	<script>
	$('#colegiado').on('change', function() {  
		var curso = $("#colegiado option:selected").text();
		window.location.href = 'controller.do?op=cadreuniao&action=selectPro&curso=' + curso;
	});
	
	$('#addPro').click(function(){
		var curso = $("#colegiado option:selected").text();
		  $('form').attr('action','controller.do?op=cadreuniao&action=addPro&curso='+ curso);
		  $('form').submit();
	});
	</script>
</body>
</html>