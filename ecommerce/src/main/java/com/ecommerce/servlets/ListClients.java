package com.ecommerce.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ecommerce.Constante;
import com.ecommerce.beans.Client;
import com.ecommerce.dao.contract.Dao;
import com.ecommerce.dao.factory.DAOFactory;
@WebServlet("/listeClients")
public class ListClients extends HttpServlet {

	private static final String VUE = "/WEB-INF/listeClients.jsp";
	private Dao<Client> dao;

	@Override
	public void init() throws ServletException {
		this.dao = ((DAOFactory) getServletContext()
				.getAttribute(Constante.CONF_DAO_FACTORY)).getDaoClient();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		setRequestClients(req);

		this.getServletContext().getRequestDispatcher(VUE).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		dao.delete(dao.get(req.getParameter("deleteId")));
		setRequestClients(req);

		this.getServletContext().getRequestDispatcher(VUE).forward(req, resp);
	}

	private void setRequestClients(HttpServletRequest req) {

		HttpSession session = req.getSession();
		session.setAttribute("clients", dao.getAll());
	}
}
