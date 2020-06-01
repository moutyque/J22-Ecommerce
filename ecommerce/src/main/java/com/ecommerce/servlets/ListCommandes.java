package com.ecommerce.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ecommerce.Constante;
import com.ecommerce.beans.Commande;
import com.ecommerce.dao.contract.Dao;
import com.ecommerce.dao.factory.DAOFactory;

public class ListCommandes extends HttpServlet {
	private static final String VUE = "/WEB-INF/listeCommandes.jsp";

	private Dao<Commande> daoCommande;

	@Override
	public void init() throws ServletException {
		this.daoCommande = ((DAOFactory) getServletContext()
				.getAttribute(Constante.CONF_DAO_FACTORY)).getDaoCommande();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(VUE).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		daoCommande.delete(daoCommande.get(req.getParameter("deleteId")));
		setRequestCommandes(req);

		this.getServletContext().getRequestDispatcher(VUE).forward(req, resp);
	}

	private void setRequestCommandes(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.setAttribute("commandes", daoCommande.getAll());
	}

}
