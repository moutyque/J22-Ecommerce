package com.ecommerce.dao.factory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.ecommerce.dao.exception.DAOConfigurationException;
import com.ecommerce.dao.impl.db.DaoClient;
import com.ecommerce.dao.impl.db.DaoCommande;

public class DaoFactoryDB extends DAOFactory {

	private static final String FICHIER_PROPERTIES = "dao.properties";
	private static final String PROPERTY_URL = "url";
	private static final String PROPERTY_DRIVER = "driver";
	private static final String PROPERTY_NOM_UTILISATEUR = "nomutilisateur";
	private static final String PROPERTY_MOT_DE_PASSE = "motdepasse";

	private String url;
	private String username;
	private String password;

	DaoFactoryDB(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
	}

	/*
	 * Méthode chargée de récupérer les informations de connexion à la base de
	 * données, charger le driver JDBC et retourner une instance de la Factory
	 */
	public static DAOFactory getInstance() throws DAOConfigurationException {
		Properties properties = new Properties();
		String url;
		String driver;
		String nomUtilisateur;
		String motDePasse;

		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		InputStream fichierProperties = classLoader
				.getResourceAsStream(FICHIER_PROPERTIES);

		if (fichierProperties == null) {
			throw new DAOConfigurationException("Le fichier properties "
					+ FICHIER_PROPERTIES + " est introuvable.");
		}

		try {
			properties.load(fichierProperties);
			url = properties.getProperty(PROPERTY_URL);
			driver = properties.getProperty(PROPERTY_DRIVER);
			nomUtilisateur = properties.getProperty(PROPERTY_NOM_UTILISATEUR);
			motDePasse = properties.getProperty(PROPERTY_MOT_DE_PASSE);
		} catch (IOException e) {
			throw new DAOConfigurationException(
					"Impossible de charger le fichier properties "
							+ FICHIER_PROPERTIES,
					e);
		}

		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			throw new DAOConfigurationException(
					"Le driver est introuvable dans le classpath.", e);
		}

		DAOFactory instance = new DaoFactoryDB(url, nomUtilisateur, motDePasse);
		instanciateDAO(instance);
		return instance;
	}

	private static void instanciateDAO(DAOFactory instance) {
		instance.setDaoClient(new DaoClient((DaoFactoryDB) instance));
		instance.setDaoCommande((new DaoCommande((DaoFactoryDB) instance)));
	}

	// TODO : change to add choice in the implementation of the DAO with
	// dependency injection using a DAOClientFacotry
	// ex : file save or sb svae or test save
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}

}
