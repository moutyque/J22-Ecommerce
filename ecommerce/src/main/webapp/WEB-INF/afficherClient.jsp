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

	<%
		Client client = (Client) pageContext.findAttribute("client");

	if (client.getPrenom().isEmpty() || client.getNom().isEmpty() || client.getAdresse().isEmpty()
			|| client.getTelephone().isEmpty()) {
	%>
	<p>
		<span class="info">Erreur - Vous n'avez pas rempli tous les
			champs obligatorie !</br> <a
			href="http://localhost:8080/ecommerce/clientCreation">Cliquer ici</a>
			pour acc�der au formulaire de cr�ation d'un client
		</span>
	</p>
	<%
		} else {
	%>
	<p>
		<span class="info">Client cr�� avec succ�s ! </span>
	</p>

	<%
		}
	%>
	<p>
		Nom : ${ client.nom } <br />
	</p>
	<p>
		Prenom : ${ client.prenom } <br />
	</p>
	<p>
		Adress : ${ client.adresse } <br />
		<!-- affiche nom du client -->
	<p>
		Num�ro de t�l�phone : ${ client.telephone } <br />
	</p>
	<p>
		Email : ${ client.email } <br />
	</p>

</body>
</html>