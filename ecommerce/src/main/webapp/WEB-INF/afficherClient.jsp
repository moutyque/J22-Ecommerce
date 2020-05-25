<%@page import="com.ecommerce.beans.Client"%>
<%@ page import="java.util.Map, java.util.HashMap"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Afficher client</title>
<link type="text/css" rel="stylesheet" href="inc/style.css" />

</head>
<body>
	<c:import url="/inc/menu.jsp" />

	<p class="info">
		<c:choose>
			<c:when
				test="${empty client.nom || empty client.prenom || empty client.adress || empty client.telephone || empty client.email}">
				<c:out
					value="Erreur - Vous n'avez pas rempli tous les	champs obligatorie !"></c:out>
				<br>
				<a href="http://localhost:8080/ecommerce/clientCreation">Cliquer
					ici</a>
				<c:out value="pour accéder au formulaire de création d'un client"></c:out>
				<br>
			</c:when>
			<c:otherwise>
				<c:out value="Client créé avec succès !"></c:out>
				<p>
					Nom : ${ client.nom } <br />
				</p>
				<p>
					Prenom : ${ client.prenom } <br />
				</p>
				<p>
					Adress : ${ client.adresse } <br />
				<p>
					Numéro de téléphone : ${ client.telephone } <br />
				</p>
				<p>
					Email : ${ client.email } <br />
				</p>
			</c:otherwise>
		</c:choose>
	</p>





</body>
</html>