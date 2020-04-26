<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>CRUD de Usuários</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
					<b>CRUD de Usuários</b>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Usuários</a></li>
			</ul>
		</nav>
	</header>
	<br>

	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">Lista de Usuários</h3>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/novo" class="btn btn-success">
					Novo Usuário</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Nome</th>
						<th>Email</th>
						<th>Nacionalidade</th>
						<th>Ações</th>
					</tr>
				</thead>
				<tbody>
					<!--   for (Todo todo: todos) {  -->
					<c:forEach var="usuario" items="${listUsuarios}">

						<tr>
							<td><c:out value="${usuario.id}" /></td>
							<td><c:out value="${usuario.nome}" /></td>
							<td><c:out value="${usuario.email}" /></td>
							<td><c:out value="${usuario.nacionalidade}" /></td>
							<td style="text-align:center;"><a href="editar?id=<c:out value='${usuario.id}' />" class="btn btn-info">Editar</a>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<a href="deletar?id=<c:out value='${usuario.id}' />" class="btn btn-danger">Excluir</a></td>
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>