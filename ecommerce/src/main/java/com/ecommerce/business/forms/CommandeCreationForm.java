package com.ecommerce.business.forms;

import static com.ecommerce.Constante.ATT_COMMANDE_DELIVERY_MODE;
import static com.ecommerce.Constante.ATT_COMMANDE_DELIVERY_STATUS;
import static com.ecommerce.Constante.ATT_COMMANDE_PAY_MODE;
import static com.ecommerce.Constante.ATT_COMMANDE_PAY_STATUS;
import static com.ecommerce.Constante.ATT_COMMANDE_TOTAL;
import static com.ecommerce.business.forms.FormHelper.getNumbValeurChamp;
import static com.ecommerce.business.forms.FormHelper.getValeurChamp;
import static com.ecommerce.business.forms.FormHelper.setErreur;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ecommerce.beans.Commande;

public class CommandeCreationForm {
	private String resultat;

	public String getResultat() {
		return resultat;
	}

	public void setResultat(String resultat) {
		this.resultat = resultat;
	}

	private Map<String, String> erreurs = new HashMap<String, String>();

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public void setErreurs(Map<String, String> erreurs) {
		this.erreurs = erreurs;
	}

	public Commande getCommande(HttpServletRequest req) {
		ClientCreationForm form = new ClientCreationForm();
		Commande commande = new Commande();

		commande.setClient(form.getClient(req));
		commande.setDate(LocalDate.now());
		commande.setModeLivraison(
				getValeurChamp(req, ATT_COMMANDE_DELIVERY_MODE));
		commande.setModePaiment(getValeurChamp(req, ATT_COMMANDE_PAY_MODE));
		commande.setMontant(getNumbValeurChamp(req, ATT_COMMANDE_TOTAL));
		commande.setStatutLivraison(getValeurChamp(req, ATT_COMMANDE_PAY_MODE));
		commande.setStatutPaiment(getValeurChamp(req, ATT_COMMANDE_PAY_STATUS));

		this.setErreurs(form.getErreurs());

		try {
			validationMontant(commande.getMontant());
		} catch (Exception e) {
			setErreur(erreurs, ATT_COMMANDE_TOTAL, e.getMessage());
		}

		try {
			validationMinimalLength(2, ATT_COMMANDE_PAY_MODE,
					commande.getModePaiment(), true);
		} catch (Exception e) {
			setErreur(erreurs, ATT_COMMANDE_PAY_MODE, e.getMessage());
		}

		try {
			validationMinimalLength(2, ATT_COMMANDE_PAY_STATUS,
					commande.getStatutPaiment(), true);
		} catch (Exception e) {
			setErreur(erreurs, ATT_COMMANDE_PAY_STATUS, e.getMessage());
		}

		try {
			validationMinimalLength(2, ATT_COMMANDE_DELIVERY_MODE,
					commande.getModeLivraison(), true);
		} catch (Exception e) {
			setErreur(erreurs, ATT_COMMANDE_DELIVERY_MODE, e.getMessage());
		}

		try {
			validationMinimalLength(2, ATT_COMMANDE_DELIVERY_STATUS,
					commande.getStatutLivraison(), false);
		} catch (Exception e) {
			setErreur(erreurs, ATT_COMMANDE_DELIVERY_STATUS, e.getMessage());
		}

		if (erreurs.isEmpty()) {
			setResultat("Succès de la création de la commande.");
		} else {
			setResultat("Échec de la création de la commande.");
		}

		return commande;
	}

	private void validationMinimalLength(int minSize, String fieldName,
			String fieldValue, boolean isMandatory) throws Exception {

		if (isMandatory) {
			if (fieldValue == null || fieldValue.length() < minSize) {
				throw new Exception(
						String.format("%s doit contenir au moins %s caractères",
								fieldName, minSize));
			}
		} else {
			if (fieldValue != null && fieldValue.length() < minSize) {
				throw new Exception(
						String.format("%s doit contenir au moins %s caractères",
								fieldName, minSize));
			}
		}

	}

	private void validationMontant(double montant) throws Exception {
		if (montant < 0) {
			throw new Exception("Le montant doit être positif.");
		}
	}
}
