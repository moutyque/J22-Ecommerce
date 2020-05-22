package com.ecommerce.servlets;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecommerce.beans.Client;
import com.ecommerce.beans.Commande;

public class afficherCommande extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

		req.setAttribute("commande", commande);
		req.setAttribute("client", client);
		this.getServletContext().getRequestDispatcher("/WEB-INF/afficherCommande.jsp").forward(req, resp);
	}
	
}
