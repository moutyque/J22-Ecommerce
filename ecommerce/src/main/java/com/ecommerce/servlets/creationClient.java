package com.ecommerce.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecommerce.beans.Client;

public class creationClient extends HttpServlet {


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/creationClient.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Client client = new Client();
		client.setNom(req.getParameter("nomClient"));
		client.setPrenom(req.getParameter("prenomClient"));
		client.setAdresse(req.getParameter("adresseClient"));
		client.setTelephone(req.getParameter("telephoneClient"));
		client.setEmail(req.getParameter("emailClient"));
		req.setAttribute("client", client);
		String message = "";
		if (client.getPrenom().isEmpty() || client.getNom().isEmpty() || client.getAdresse().isEmpty()
				|| client.getTelephone().isEmpty()) {

			message ="Erreur - Vous n'avez pas rempli tous les	champs obligatorie !<br> <a	href=\"http://localhost:8080/ecommerce/clientCreation\">Cliquer ici</a>	pour acc�der au formulaire de cr�ation d'un client";

		} else {

			message = "Client cr�� avec succ�s !";
		}
		
		req.setAttribute("message", message);
		this.getServletContext().getRequestDispatcher("/WEB-INF/afficherClient.jsp").forward(req, resp);
	}

}
