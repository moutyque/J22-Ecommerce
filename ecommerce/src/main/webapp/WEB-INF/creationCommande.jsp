<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Création d'une commande</title>
<link type="text/css" rel="stylesheet" href="inc/style.css" />
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>

<script>
		$(document).ready(function() {
			$("#oui").click(function(){
				  $("#newClient").show();
				  $("#oldClient").hide();
				});
			
			
			$("#non").click(function(){
				  $("#oldClient").show();
				  $("#newClient").hide();
				});
	
			  $("#newClient").show();
			  $("#oldClient").hide();
		});
	
</script>
</head>
<body>
	<c:import url="/inc/menu.jsp" />


	<form method="post" action="commandeCreation" enctype='multipart/form-data'>

		<fieldset>
			<label>
				Nouveau client<span class="requis">*</span>
			</label>

			<input type="radio" id="oui" name="choice" value="oui" checked>
			<label for="oui">Oui</label>

			<input type="radio" id="non" name="choice" value="non">
			<label for="non">Non</label>

			<div id="newClient">
				<c:import url="/WEB-INF/formulaireCreationClient.jsp" />
			</div>
			
			<div id="oldClient">
				<select name="oldClients" id="oldClients">
				
				<c:forEach items="${clients}" var="entry">
				<c:set var="client" value="${entry.value}" scope="page"></c:set>
				<option value="${client.id}">${client}</option>
				</c:forEach>
				</select>
			</div>
		</fieldset>
		<fieldset>
			<legend>Informations commande</legend>

			<label for="dateCommande">
				Date <span class="requis">*</span>
			</label>
			<input type="text" id="dateCommande" name="dateCommande"
				value="<c:out value="${commande.date}"/>" size="20" maxlength="20"
				disabled />
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

</body>
</html>