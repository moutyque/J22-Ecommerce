package com.ecommerce.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ecommerce.beans.Client;
import com.ecommerce.dao.DaoFactory;
import com.ecommerce.dao.contract.Dao;

public class ListClients extends HttpServlet {

	private static final String VUE = "/WEB-INF/listeClients.jsp";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		setRequestClients(req);

		this.getServletContext().getRequestDispatcher(VUE).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		DaoFactory.getDaoClient().delete(
				DaoFactory.getDaoClient().get(req.getParameter("deleteId")));
		setRequestClients(req);

		this.getServletContext().getRequestDispatcher(VUE).forward(req, resp);
	}

	private void setRequestClients(HttpServletRequest req) {
		Dao<Client> dao = DaoFactory.getDaoClient();
		HttpSession session = req.getSession();
		session.setAttribute("clients", dao.getAll());
	}
}
