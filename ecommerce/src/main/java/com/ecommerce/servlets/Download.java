package com.ecommerce.servlets;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecommerce.Constante;
import com.ecommerce.util.Helper;

@WebServlet("/download/*")
public class Download extends HttpServlet {
	private static final int DEFAULT_BUFFER_SIZE = 1024;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String fichierRequis = req.getPathInfo();

		if (fichierRequis == null || "/".equals(fichierRequis)) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND,
					"Aucun fichier trouvé dans l'url");
			return;
		}

		fichierRequis = URLDecoder.decode(fichierRequis, "UTF-8");
		File fichier = new File(Constante.FILES_PATH, fichierRequis);
		if (!fichier.exists()) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND,
					"Le fichier n'a pas été trouvé sur le serveur");
			return;
		}

		String type = getServletContext().getMimeType(fichier.getName());
		if (type == null) {
			type = "application/octet-stream";
		}

		/* Initialise la réponse HTTP */
		resp.reset();
		resp.setBufferSize(DEFAULT_BUFFER_SIZE);
		resp.setContentType(type);
		resp.setHeader("Content-Length", String.valueOf(fichier.length()));
		resp.setHeader("Content-Disposition",
				"inline; filename=\"" + fichier.getName() + "\"");
		try {
			Helper.witteFile(fichier, resp.getOutputStream());

		} catch (Exception e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST,
					"Le fichier n'a pas pu être téléchargé");
			return;
		}
	}

}
