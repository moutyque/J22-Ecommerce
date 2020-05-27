package com.ecommerce.business.forms;

import static com.ecommerce.Constante.ATT_CLIENT_ADRESS;
import static com.ecommerce.Constante.ATT_CLIENT_EMAIL;
import static com.ecommerce.Constante.ATT_CLIENT_FIRST_NAME;
import static com.ecommerce.Constante.ATT_CLIENT_LAST_NAME;
import static com.ecommerce.Constante.ATT_CLIENT_PHONE;
import static com.ecommerce.business.forms.FormHelper.getValeurChamp;
import static com.ecommerce.business.forms.FormHelper.setErreur;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ecommerce.beans.Client;

public class ClientCreationForm {

	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public void setErreurs(Map<String, String> erreurs) {
		this.erreurs = erreurs;
	}

	public Client getClient(HttpServletRequest req) {

		Client client = new Client();
		client.setNom(getValeurChamp(req, ATT_CLIENT_LAST_NAME));
		client.setPrenom(getValeurChamp(req, ATT_CLIENT_FIRST_NAME));
		client.setAdresse(getValeurChamp(req, ATT_CLIENT_ADRESS));
		client.setTelephone(getValeurChamp(req, ATT_CLIENT_PHONE));
		client.setEmail(getValeurChamp(req, ATT_CLIENT_EMAIL));

		try {
			validationEmail(client.getEmail());
		} catch (Exception e) {
			setErreur(erreurs, ATT_CLIENT_EMAIL, e.getMessage());
		}
		try {
			validationPrenom(client.getPrenom());
		} catch (Exception e) {
			setErreur(erreurs, ATT_CLIENT_FIRST_NAME, e.getMessage());
		}

		try {
			validationNom(client.getNom());
		} catch (Exception e) {
			setErreur(erreurs, ATT_CLIENT_LAST_NAME, e.getMessage());
		}

		try {
			validationAdress(client.getAdresse());
		} catch (Exception e) {
			setErreur(erreurs, ATT_CLIENT_ADRESS, e.getMessage());
		}

		try {
			validationTelephone(client.getAdresse());
		} catch (Exception e) {
			setErreur(erreurs, ATT_CLIENT_PHONE, e.getMessage());
		}

		if (erreurs.isEmpty()) {
			setResultat("Succès de l'inscription.");
		} else {
			setResultat("Échec de l'inscription.");
		}
		return client;
	}

	private void validationEmail(String email) throws Exception {

		if (email != null && !email
				.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
			throw new Exception("Merci de saisir une adresse mail valide.");
		}

	}

	private void validationPrenom(String nom) throws Exception {
		if (nom != null && nom.length() < 2) {
			throw new Exception(
					"Le prénom d'utilisateur doit contenir au moins 3 caractères.");
		}
	}

	private void validationNom(String nom) throws Exception {
		if (nom == null || nom.length() < 2) {
			throw new Exception(
					"Le nom d'utilisateur doit contenir au moins 3 caractères.");
		}
	}

	private void validationAdress(String adresse) throws Exception {
		if (adresse == null || adresse.length() < 10) {
			throw new Exception(
					"L'adressse d'utilisateur doit contenir au moins 10 caractères.");
		}
	}

	private void validationTelephone(String telephone) throws Exception {
		if (telephone == null || telephone.length() < 10) {
			throw new Exception(
					"Le téléphone de l'utilisateur doit contenir au moins 4 caractères.");
		}
	}

	public String getResultat() {
		return resultat;
	}

	public void setResultat(String resultat) {
		this.resultat = resultat;
	}
}
