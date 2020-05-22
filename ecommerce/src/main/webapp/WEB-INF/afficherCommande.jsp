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

	<%
		Commande commande = (Commande) pageContext.findAttribute("commande");
	Client client = null;
		if(commande!=null)client = commande.getClient();

	if (commande==null || client== null || client.getPrenom().isEmpty() || client.getNom().isEmpty() || client.getAdresse().isEmpty()	|| client.getTelephone().isEmpty() ||
			commande.getMontant()==0 || commande.getModeLivraison().isEmpty() || commande.getModePaiment().isEmpty() || commande.getStatutPaiment().isEmpty()) {
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
		<span class="info">Commande cr�� avec succ�s ! </span>
	</p>

	<%
		}
	%>
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
		Num�ro de t�l�phone : ${ client.telephone } <br />
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
</body>
</html>