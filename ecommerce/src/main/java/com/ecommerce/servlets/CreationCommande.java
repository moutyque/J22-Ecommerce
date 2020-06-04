package com.ecommerce.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ecommerce.Constante;
import com.ecommerce.beans.Client;
import com.ecommerce.beans.Commande;
import com.ecommerce.business.forms.CommandeCreationForm;
import com.ecommerce.dao.contract.Dao;
import com.ecommerce.dao.factory.DAOFactory;
@WebServlet("/commandeCreation")
@MultipartConfig(location = "D:\\eclipse-workspace\\pro\\fichiers", fileSizeThreshold = 1024
		* 1024, maxFileSize = 1024 * 1024
				* 1024, maxRequestSize = 5 * 1024 * 1024 * 1024)
public class CreationCommande extends HttpServlet {
	private Dao<Commande> daoCommande;
	private Dao<Client> daoClient;

	@Override
	public void init() throws ServletException {
		this.daoCommande = ((DAOFactory) getServletContext()
				.getAttribute(Constante.CONF_DAO_FACTORY)).getDaoCommande();
		this.daoClient = ((DAOFactory) getServletContext()
				.getAttribute(Constante.CONF_DAO_FACTORY)).getDaoClient();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.setAttribute("clients", daoClient.getAll());
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
		daoCommande.save(commande);

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
