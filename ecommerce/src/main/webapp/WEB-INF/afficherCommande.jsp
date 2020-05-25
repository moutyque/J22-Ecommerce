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
		<c:choose>
			<c:when
				test="${empty client.nom || empty client.prenom || empty client.adress || empty client.telephone || empty client.email
			|| commande.montant<0 || empty commande.modeLivraison || empty commande.modePaiment || empty commande.statutPaiement}">

				<c:out
					value="Erreur - Vous n'avez pas rempli tous les	champs obligatorie !"></c:out>
				<br>
				<a href="http://localhost:8080/ecommerce/commandeCreation">Cliquer
					ici</a>
				<c:out value="pour accéder au formulaire de création d'une commande"></c:out>
				<br>
			</c:when>
			<c:otherwise>
				<c:out value="Commande créée avec succès !"></c:out>
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
			</c:otherwise>
		</c:choose>


	</p>

</body>
</html>