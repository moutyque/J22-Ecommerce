package com.ecommerce.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ecommerce.Constante;
import com.ecommerce.beans.Client;
import com.ecommerce.business.forms.ClientCreationForm;
import com.ecommerce.dao.contract.Dao;
import com.ecommerce.dao.factory.DAOFactory;
public class CreationClient extends HttpServlet {

	private Dao<Client> dao;

	@Override
	public void init() throws ServletException {
		this.dao = ((DAOFactory) getServletContext()
				.getAttribute(Constante.CONF_DAO_FACTORY)).getDaoClient();
	}

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
		ClientCreationForm form = new ClientCreationForm(req);
		Client client = form.getClient(req);
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
