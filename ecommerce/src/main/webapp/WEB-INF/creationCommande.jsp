<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Création d'une commande</title>
<link type="text/css" rel="stylesheet" href="inc/style.css" />
</head>
<body>
	<c:import url="/inc/menu.jsp" />

	<div>
		<form method="post" action="commandeCreation">
			<c:import url="/WEB-INF/formulaireCreationClient.jsp" />
			<fieldset>
				<legend>Informations commande</legend>

				<label for="dateCommande">
					Date <span class="requis">*</span>
				</label>
				<input type="text" id="dateCommande" name="dateCommande"
					value="<c:out value="${commande.date}"/>" size="20"
					maxlength="20" disabled />
				<br />
				<label for="montantCommande">
					Montant<span class="requis">*</span>
				</label>
				<input type="number" id="montantCommande" name="montantCommande"
					value="<c:out value="${commande.montant}"/>" size="20"
					maxlength="20" />
				<span class="erreur"><c:out value="${errors.montantCommande}" /></span>
				<br />
				<label for="modePaiementCommande">
					Mode de paiement <span class="requis">*</span>
				</label>
				<input type="text" id="modePaiementCommande"
					name="modePaiementCommande"
					value="<c:out value="${commande.modePaiment}"/>" size="20"
					maxlength="20" />
				<span class="erreur"><c:out
						value="${errors.modePaiementCommande}" /></span> <br />
				<label for="statutPaiementCommande">Statut du paiement</label>
				<input type="text" id="statutPaiementCommande"
					name="statutPaiementCommande"
					value="<c:out value="${commande.statutPaiment}"/>" size="20"
					maxlength="20" />
				<span class="erreur"><c:out
						value="${errors.statutPaiementCommande}" /></span> <br />
				<label for="modeLivraisonCommande">
					Mode de livraison <span class="requis">*</span>
				</label>
				<input type="text" id="modeLivraisonCommande"
					name="modeLivraisonCommande"
					value="<c:out value="${commande.modeLivraison}"/>" size="20"
					maxlength="20" />
				<span class="erreur"><c:out
						value="${errors.modeLivraisonCommande}" /></span> <br />
				<label for="statutLivraisonCommande">Statut de la livraison</label>
				<input type="text" id="statutLivraisonCommande"
					name="statutLivraisonCommande"
					value="<c:out value="${commande.statutLivraison}"/>" size="20"
					maxlength="20" />
				<span class="erreur"><c:out
						value="${errors.statutLivraisonCommande}" /></span> <br />
			</fieldset>
			<span class="info">${resultat}</span><br />
			<input type="submit" value="Valider" />
			<input type="reset" value="Remettre à zéro" />
			<br />
		</form>
	</div>
</body>
</html>