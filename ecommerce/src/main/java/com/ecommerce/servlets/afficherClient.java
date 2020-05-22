package com.ecommerce.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecommerce.beans.Client;

public class afficherClient extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		Client client = new Client();
		client.setNomClient(req.getParameter("nomClient"));
		client.setPrenomClient(req.getParameter("prenomClient"));
		client.setAdresseClient(req.getParameter("adresseClient"));
		client.setTelephoneClient(req.getParameter("telephoneClient"));
		client.setEmailClient(req.getParameter("emailClient"));
		req.setAttribute("client", client);
				
		this.getServletContext().getRequestDispatcher("/WEB-INF/afficherClient.jsp").forward(req, resp);

	}
}
