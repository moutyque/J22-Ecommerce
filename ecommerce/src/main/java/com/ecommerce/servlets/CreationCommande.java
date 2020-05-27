package com.ecommerce.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ecommerce.beans.Client;
import com.ecommerce.beans.Commande;
import com.ecommerce.business.forms.CommandeCreationForm;
import com.ecommerce.dao.DaoFactory;
import com.ecommerce.dao.contract.Dao;

public class CreationCommande extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.setAttribute("clients", DaoFactory.getDaoClient().getAll());
		this.getServletContext()
				.getRequestDispatcher("/WEB-INF/creationCommande.jsp")
				.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		CommandeCreationForm form = new CommandeCreationForm();
		Commande commande = form.getCommande(req);
		Client client = commande.getClient();
		Dao<Commande> daoCommande = DaoFactory.getDaoCommande();
		daoCommande.save(commande);

		Dao<Client> daoClient = DaoFactory.getDaoClient();
		daoClient.save(commande.getClient());

		req.setAttribute("client", client);
		req.setAttribute("commande", commande);
		req.setAttribute("errors", form.getErreurs());
		req.setAttribute("resultat", form.getResultat());

		HttpSession session = req.getSession();
		session.setAttribute("commandes", daoCommande.getAll());

		if (form.getErreurs().isEmpty()) {
			this.getServletContext()
					.getRequestDispatcher("/WEB-INF/afficherCommande.jsp")
					.forward(req, resp);
		} else {
			this.getServletContext()
					.getRequestDispatcher("/WEB-INF/creationCommande.jsp")
					.forward(req, resp);
		}

	}
}
