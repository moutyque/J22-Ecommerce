<%@page import="com.ecommerce.beans.Client"%>
<%@page import="com.ecommerce.beans.Commande"%>
<%@ page import="java.util.Map, java.util.HashMap"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Afficher commande</title>
<link type="text/css" rel="stylesheet" href="inc/style.css" />

</head>
<body>
	<c:import url="/inc/menu.jsp" />
	<p class="info">

				<c:out value="${ resultat}"></c:out>
				<p>Client</p>
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
					Numéro de téléphone : ${ client.telephone } <br />
				</p>
				<p>
					Email : ${ client.email } <br />
				</p>
				<p>Commande</p>
				<p>
					Date : ${ commande.date } <br />
				</p>
				<p>
					Montant : ${ commande.montant } <br />
				</p>
				<p>
					Mode de paiement : ${ commande.modePaiment } <br />
				</p>
				<p>
					Statut de paiement : ${ commande.statutPaiment } <br />
				</p>
				<p>
					Mode de livraison : ${ commande.modeLivraison } <br />
				</p>
				<p>
					Statut de livraison : ${ commande.statutLivraison } <br />
				</p>
	


	</p>

</body>
</html>