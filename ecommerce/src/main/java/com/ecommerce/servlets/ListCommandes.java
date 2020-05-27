package com.ecommerce.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ecommerce.beans.Commande;
import com.ecommerce.dao.DaoFactory;
import com.ecommerce.dao.contract.Dao;

public class ListCommandes extends HttpServlet {
	private static final String VUE = "/WEB-INF/listeCommandes.jsp";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(VUE).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		DaoFactory.getDaoCommande().delete(
				DaoFactory.getDaoCommande().get(req.getParameter("deleteId")));
		setRequestCommandes(req);

		this.getServletContext().getRequestDispatcher(VUE).forward(req, resp);
	}

	private void setRequestCommandes(HttpServletRequest req) {
		Dao<Commande> dao = DaoFactory.getDaoCommande();
		HttpSession session = req.getSession();
		session.setAttribute("commandes", dao.getAll());
	}

}
