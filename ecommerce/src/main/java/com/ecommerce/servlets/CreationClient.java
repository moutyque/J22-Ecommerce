package com.ecommerce.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecommerce.beans.Client;
import com.ecommerce.business.forms.ClientCreationForm;

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
		req.setAttribute("client", client);
		req.setAttribute("errors", form.getErreurs());
		req.setAttribute("resultat", form.getResultat());

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
