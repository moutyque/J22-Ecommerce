package com.ecommerce.servlets;

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
        client.setNom( req.getParameter( "nomClient" ) );
        client.setPrenom( req.getParameter( "prenomClient" ) );
        client.setAdresse( req.getParameter( "adresseClient" ) );
        client.setTelephone( req.getParameter( "telephoneClient" ) );
        client.setEmail( req.getParameter( "emailClient" ) );
        req.setAttribute( "client", client );
        this.getServletContext().getRequestDispatcher( "/WEB-INF/afficherClient.jsp" ).forward( req, resp );
    }

}
