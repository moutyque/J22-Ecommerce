package com.ecommerce.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ecommerce.beans.Client;
import com.ecommerce.business.forms.ClientCreationForm;
import com.ecommerce.dao.DaoFactory;
import com.ecommerce.dao.contract.Dao;

public class CreationClient extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.getServletContext()
				.getRequestDispatcher("/WEB-INF/creationClient.jsp")
				.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		ClientCreationForm form = new ClientCreationForm();
		Client client = form.getClient(req);
		Dao<Client> dao = DaoFactory.getDaoClient();
		dao.save(client);

		req.setAttribute("client", client);
		req.setAttribute("errors", form.getErreurs());
		req.setAttribute("resultat", form.getResultat());

		HttpSession session = req.getSession();
		session.setAttribute("clients", dao.getAll());

		if (form.getErreurs().isEmpty()) {
			this.getServletContext()
					.getRequestDispatcher("/WEB-INF/afficherClient.jsp")
					.forward(req, resp);
		} else {
			this.getServletContext()
					.getRequestDispatcher("/WEB-INF/creationClient.jsp")
					.forward(req, resp);
		}
	}

}
