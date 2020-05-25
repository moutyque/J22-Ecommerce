package com.ecommerce.servlets;

import static com.ecommerce.Constante.ATT_CLIENT_ADRESS;
import static com.ecommerce.Constante.ATT_CLIENT_EMAIL;
import static com.ecommerce.Constante.ATT_CLIENT_FIRST_NAME;
import static com.ecommerce.Constante.ATT_CLIENT_LAST_NAME;
import static com.ecommerce.Constante.ATT_CLIENT_PHONE;
import static com.ecommerce.Constante.ATT_COMMANDE_DELIVERY_MODE;
import static com.ecommerce.Constante.ATT_COMMANDE_DELIVERY_STATUS;
import static com.ecommerce.Constante.ATT_COMMANDE_PAY_MODE;
import static com.ecommerce.Constante.ATT_COMMANDE_PAY_STATUS;
import static com.ecommerce.Constante.ATT_COMMANDE_TOTAL;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecommerce.beans.Client;
import com.ecommerce.beans.Commande;

public class creationCommande extends HttpServlet {
    @Override
    protected void doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher( "/WEB-INF/creationCommande.jsp" ).forward( req, resp );
    }

    @Override
    protected void doPost( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
        Client client = new Client();
        client.setNom( req.getParameter( ATT_CLIENT_LAST_NAME ) );
        client.setPrenom( req.getParameter( ATT_CLIENT_FIRST_NAME ) );
        client.setAdresse( req.getParameter( ATT_CLIENT_ADRESS ) );
        client.setTelephone( req.getParameter( ATT_CLIENT_PHONE ) );
        client.setEmail( req.getParameter( ATT_CLIENT_EMAIL ) );

        Commande commande = new Commande();
        commande.setClient( client );
        commande.setDate( LocalDate.now() );
        commande.setModeLivraison( req.getParameter( ATT_COMMANDE_DELIVERY_MODE ) );
        commande.setModePaiment( req.getParameter( ATT_COMMANDE_PAY_MODE ) );

        try {
            commande.setMontant( Double.parseDouble( req.getParameter( ATT_COMMANDE_TOTAL ) ) );
        } catch ( Exception e ) {
            commande.setMontant( -1 );
        }

        commande.setStatutLivraison( req.getParameter( ATT_COMMANDE_DELIVERY_STATUS ) );
        commande.setStatutPaiment( req.getParameter( ATT_COMMANDE_PAY_STATUS ) );

        req.setAttribute( "client", client );
        req.setAttribute( "commande", commande );
        this.getServletContext().getRequestDispatcher( "/WEB-INF/afficherCommande.jsp" ).forward( req, resp );

    }
}
