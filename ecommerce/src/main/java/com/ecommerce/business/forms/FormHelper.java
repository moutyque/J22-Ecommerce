package com.ecommerce.business.forms;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public final class FormHelper {
	/*
	 * Ajoute un message correspondant au champ spécifié à la map des erreurs.
	 */
	public static void setErreur(Map<String, String> erreurs, String champ,
			String message) {
		erreurs.put(champ, message);
	}

	/*
	 * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
	 * sinon.
	 */
	public static String getValeurChamp(HttpServletRequest request,
			String nomChamp) {
		String valeur = request.getParameter(nomChamp);
		if (valeur == null || valeur.trim().length() == 0) {
			return null;
		} else {
			return valeur.trim();
		}
	}

	public static double getNumbValeurChamp(HttpServletRequest request,
			String nomChamp) {
		String valeur = request.getParameter(nomChamp);
		if (valeur == null || valeur.trim().length() == 0) {
			return 0;
		} else {
			double val = 0;
			try {
				val = Double.parseDouble(valeur.trim());
			} catch (Exception e) {
				return 0;
			}
			return val;
		}
	}
}
