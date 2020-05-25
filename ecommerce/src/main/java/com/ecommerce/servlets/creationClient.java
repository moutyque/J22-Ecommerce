package com.ecommerce.servlets;

import static com.ecommerce.Constante.ATT_CLIENT_EMAIL;
import static com.ecommerce.Constante.ATT_CLIENT_FIRST_NAME;
import static com.ecommerce.Constante.ATT_CLIENT_LAST_NAME;
import static com.ecommerce.Constante.ATT_CLIENT_PHONE;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecommerce.beans.Client;

public class creationClient extends HttpServlet {

    @Override
    protected void doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher( "/WEB-INF/creationClient.jsp" ).forward( req, resp );
    }

    @Override
    protected void doPost( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
        Client client = new Client();
        client.setNom( req.getParameter( ATT_CLIENT_LAST_NAME ) );
        client.setPrenom( req.getParameter( ATT_CLIENT_FIRST_NAME ) );
        client.setAdresse( req.getParameter( ATT_CLIENT_PHONE ) );
        client.setTelephone( req.getParameter( ATT_CLIENT_PHONE ) );
        client.setEmail( req.getParameter( ATT_CLIENT_EMAIL ) );
        req.setAttribute( "client", client );
        this.getServletContext().getRequestDispatcher( "/WEB-INF/afficherClient.jsp" ).forward( req, resp );
    }

}
