package com.ecommerce.business.forms;

import static com.ecommerce.Constante.ATT_CLIENT_ADRESS;
import static com.ecommerce.Constante.ATT_CLIENT_EMAIL;
import static com.ecommerce.Constante.ATT_CLIENT_FIRST_NAME;
import static com.ecommerce.Constante.ATT_CLIENT_LAST_NAME;
import static com.ecommerce.Constante.ATT_CLIENT_PHONE;
import static com.ecommerce.Constante.ATT_CLIENT_PICTURE;
import static com.ecommerce.Constante.ATT_OLD_CLIENTS;
import static com.ecommerce.Constante.FILES_PATH;
import static com.ecommerce.business.forms.FormHelper.getValeurChamp;
import static com.ecommerce.business.forms.FormHelper.setErreur;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import com.ecommerce.beans.Client;
import com.ecommerce.dao.DaoFactory;
import com.ecommerce.util.Helper;

import eu.medsea.mimeutil.MimeUtil;

public class ClientCreationForm {

	private String resultat;
	private Map<String, String> erreurs = new HashMap<>();

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public void setErreurs(Map<String, String> erreurs) {
		this.erreurs = erreurs;
	}

	public Client getClient(HttpServletRequest req) {

		Client client;
		if (getValeurChamp(req, ATT_OLD_CLIENTS) != null) {

			client = DaoFactory.getDaoClient()
					.get(getValeurChamp(req, ATT_OLD_CLIENTS));
		} else {
			client = new Client();
			client.setNom(getValeurChamp(req, ATT_CLIENT_LAST_NAME));
			client.setPrenom(getValeurChamp(req, ATT_CLIENT_FIRST_NAME));
			client.setAdresse(getValeurChamp(req, ATT_CLIENT_ADRESS));
			client.setTelephone(getValeurChamp(req, ATT_CLIENT_PHONE));
			client.setEmail(getValeurChamp(req, ATT_CLIENT_EMAIL));
			client.setFichier(saveFile(req));
		}

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

	private Path saveFile(HttpServletRequest request) {

		String fileName = null;
		InputStream fileContent = null;

		Part part;
		try {
			part = request.getPart("image");
			fileName = getNomFichier(part);
			if (fileName != null && !fileName.isEmpty()) {
				// Fix ie bug on filename
				fileName = fileName.substring(fileName.lastIndexOf('/') + 1)
						.substring(fileName.lastIndexOf('\\') + 1);
				// As file exist get its content
				fileContent = part.getInputStream();

			}
		} catch (IOException e) {
			e.printStackTrace();
			setErreur(erreurs, ATT_CLIENT_PICTURE,
					"Ce type de requête n'est pas supporté, merci d'utiliser le formulaire prévu pour envoyer votre fichier.");
		} catch (ServletException e) {
			e.printStackTrace();
			setErreur(erreurs, ATT_CLIENT_PICTURE,
					"Erreur de configuration du serveur.");
		}

		try {
			validationImage(fileContent);
		} catch (Exception e) {
			setErreur(erreurs, ATT_CLIENT_PICTURE, e.getMessage());
		}

		if (erreurs.isEmpty()) {
			try {
				Helper.witteFile(fileContent, new File(FILES_PATH, fileName));
			} catch (Exception e) {
				setErreur(erreurs, ATT_CLIENT_PICTURE,
						"Erreur lors de l'écriture du fichier sur le disque.");
			}
		}

		return Paths.get(FILES_PATH + fileName);

	}

	private static String getNomFichier(Part part) {

		Optional<String> filename = Arrays
				.asList(part.getHeader("content-disposition").split(";"))
				.stream().filter(s -> s.trim().startsWith("filename"))
				.findFirst();
		if (filename.isPresent()) {
			return filename.get().substring(filename.get().indexOf('=') + 1)
					.trim().replace("\"", "");
		}
		return null;
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

	/*
	 * Valide le fichier envoyé.
	 */
	private void validationImage(InputStream contenuFichier) throws Exception {
		if (contenuFichier == null) {
			throw new Exception("Merci de sélectionner un fichier à envoyer.");
		}
		/*
		 * Extraction du type MIME du fichier depuis l'InputStream nommé
		 * "contenu"
		 */
		MimeUtil.registerMimeDetector(
				"eu.medsea.mimeutil.detector.MagicMimeMimeDetector");
		Collection<?> mimeTypes = MimeUtil.getMimeTypes(contenuFichier);

		/*
		 * Si le fichier est bien une image, alors son en-tête MIME commence par
		 * la chaîne "image"
		 */
		if (!mimeTypes.toString().startsWith("image")) {
			throw new Exception("Le fichier choisie n'est pas une image");
		}

	}

	public String getResultat() {
		return resultat;
	}

	public void setResultat(String resultat) {
		this.resultat = resultat;
	}
}
