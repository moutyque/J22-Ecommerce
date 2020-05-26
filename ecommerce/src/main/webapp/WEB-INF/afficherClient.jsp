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
	<c:out value="${resultat}"></c:out>
	<p class="info">
	<p>
		Nom : ${ client.nom } <br />
	</p>
	<p>
		Prenom : ${ client.prenom } <br />
	</p>
	<p>
		Adress : ${ client.adresse } <br />
	</p>

	<p>
		Numéro de téléphone : ${ client.telephone } <br />
	</p>
	<p>
		Email : ${ client.email } <br />
	</p>

	</p>
</body>
</html>