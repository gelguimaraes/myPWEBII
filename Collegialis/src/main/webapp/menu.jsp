<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<nav class="navbar"></nav>
<nav class="navbar navbar-fixed-top navbar-inverse">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="home.jsp"> <img
				src="images/logo-menu.png" style="height: 30px; margin-top: -5px;">
			</a>
		</div>
		<div id="navbar" class="collapse navbar-collapse">
			<ul class="nav navbar-nav">
				<li><a href="home.jsp"><i class="fas fa-home"></i> Inicio</a></li>
				<li>
					<ul class="nav navbar-nav">
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false"> <i class="fas fa-file-signature"></i>
								Processos
						</a>
							<ul class="dropdown-menu">
								<li><a href="controller.do?op=cadpro">Cadastrar</a></li>
								<li role="separator" class="divider"></li>
								<li><a href="controller.do?op=listprocessos">Listar</a></li>
							</ul></li>
					</ul>
				</li>
				<li>
					<ul class="nav navbar-nav">
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false"> <i class="fas fa-users"></i> Reuniões
						</a>
							<ul class="dropdown-menu">
								<li><a href="controller.do?op=cadreuniao">Planejar</a></li>
								<li role="separator" class="divider"></li>
								<li><a href="controller.do?op=listreuniao">Listar</a></li>
							</ul></li>
					</ul>
				</li>
				<li>
					<ul class="nav navbar-nav">
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false"> <i class="fas fa-chalkboard-teacher"></i>
								Colegiado
						</a>
							<ul class="dropdown-menu">
								<li><a href="controller.do?op=cadcol">Cadastrar</a></li>
								<li role="separator" class="divider"></li>
								<li><a href="controller.do?op=listcolegiado">Listar</a></li>

							</ul></li>
					</ul>
				</li>
			</ul>

			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false"> <i class="fas fa-user-circle"></i>
						${nameUser} (${typeUser}) <span style="font-size: .6em;"
						class="glyphicon glyphicon-menu-down btn-xs" aria-hidden="true"></span>
				</a>
					<ul class="dropdown-menu">
						<li><a href="controller.do?op=logout">Sair</a></li>
					</ul></li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container -->
</nav>