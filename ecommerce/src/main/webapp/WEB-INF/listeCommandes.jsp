<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List des commandes sauvegardés</title>
<link type="text/css" rel="stylesheet" href="inc/style.css" />
</head>
<body>
	<c:import url="/inc/menu.jsp" />

	<table>
		<tr>
			<th class="header">Client</th>
			<th class="header">Date</th>
			<th class="header">Montant</th>
			<th class="header">Mode paiment</th>
			<th class="header">Statut de paiment</th>
			<th class="header">Mode de livraison</th>
			<th class="header">Statut de livraison</th>
			<th class="headerAction">Action</th>
		</tr>
		<c:forEach items="${sessionScope.commandes}" var="entry">
			<c:set var="commande" value="${ entry.value}" scope="request"></c:set>
			<tr>
				<td>${commande.client }</td>
				<td>${commande.date }</td>
				<td>${commande.montant }</td>
				<td>${commande.modePaiment }</td>
				<td>${commande.statutPaiment }</td>
				<td>${commande.modeLivraison }</td>
				<td>${commande.statutLivraison }</td>
				<td><form method="post" action="listeCommandes">
						<input type="submit" value="Supprimer" />
						<input type='hidden' name='deleteId' id='deleteId'
							value="${commande.id}" />

					</form></td>
			</tr>
		</c:forEach>

	</table>
</body>
</html>