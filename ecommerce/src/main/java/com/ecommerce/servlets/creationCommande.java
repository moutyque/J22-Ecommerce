package com.ecommerce.servlets;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecommerce.beans.Client;
import com.ecommerce.beans.Commande;

public class creationCommande extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/creationCommande.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Client client = new Client();
		client.setNom(req.getParameter("nomClient"));
		client.setPrenom(req.getParameter("prenomClient"));
		client.setAdresse(req.getParameter("adresseClient"));
		client.setTelephone(req.getParameter("telephoneClient"));
		client.setEmail(req.getParameter("emailClient"));

		Commande commande = new Commande();
		commande.setClient(client);
		commande.setDate(LocalDate.now());
		commande.setModeLivraison(req.getParameter("modeLivraisonCommande"));
		commande.setModePaiment(req.getParameter("modePaiementCommande"));

		try{
			commande.setMontant(Double.parseDouble(req.getParameter("montantCommande")));
		}
		catch(Exception e) {
			commande.setMontant(0);
		}

		commande.setStatutLivraison(req.getParameter("statutLivraisonCommande"));
		commande.setStatutPaiment(req.getParameter("statutPaiementCommande"));
		String message ="";

		if (client.getPrenom().isEmpty() || client.getNom().isEmpty() || client.getAdresse().isEmpty()	|| client.getTelephone().isEmpty() ||
				commande.getMontant()==0 || commande.getModeLivraison().isEmpty() || commande.getModePaiment().isEmpty() || commande.getStatutPaiment().isEmpty()) {
			message ="Erreur - Vous n'avez pas rempli tous les	champs obligatorie !<br> <a	href=\"http://localhost:8080/ecommerce/commandeCreation\">Cliquer ici</a>	pour accéder au formulaire de création d'un client";

		} else {
			message ="Commande créé avec succès ! ";
		}


		req.setAttribute("client", client);
		req.setAttribute("commande", commande);
		req.setAttribute("message", message);
		this.getServletContext().getRequestDispatcher("/WEB-INF/afficherCommande.jsp").forward(req, resp);

	}
}
