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

	if (client.getPrenomClient().isEmpty() || client.getNomClient().isEmpty() || client.getAdresseClient().isEmpty()
			|| client.getTelephoneClient().isEmpty()) {
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
		Nom : ${ client.nomClient } <br />
	</p>
	<p>
		Prenom : ${ client.prenomClient } <br />
	</p>
	<p>
		Adress : ${ client.adresseClient } <br />
		<!-- affiche nom du client -->
	<p>
		Num�ro de t�l�phone : ${ client.telephoneClient } <br />
	</p>
	<p>
		Email : ${ client.emailClient } <br />
	</p>

</body>
</html>