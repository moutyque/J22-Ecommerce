<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List des clients sauvegardés</title>
<link type="text/css" rel="stylesheet" href="inc/style.css" />
</head>
<body>
	<c:import url="/inc/menu.jsp" />

	<table>
		<tr>
			<th class="header">Prénom</th>
			<th class="header">Nom</th>
			<th class="header">Adress</th>
			<th class="header">Téléphone</th>
			<th class="header">Email</th>
			<th class="headerAction">Action</th>
		</tr>
		<c:forEach items="${sessionScope.clients}" var="entry">
			<c:set var="client" value="${ entry.value}" scope="request"></c:set>
			<tr>
				<td>${client.prenom }</td>
				<td>${client.nom }</td>
				<td>${client.adresse }</td>
				<td>${client.telephone }</td>
				<td>${client.email }</td>
				<td><form method="post" action="listeClients">
						<input type="submit" value="Supprimer" />
						<input type='hidden' name='deleteId' id='deleteId'
							value="${client.id}" />

					</form></td>
			</tr>
		</c:forEach>

	</table>
</body>
</html>