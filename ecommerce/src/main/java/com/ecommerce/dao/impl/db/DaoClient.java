package com.ecommerce.dao.impl.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.ecommerce.beans.Client;
import com.ecommerce.dao.contract.Dao;
import com.ecommerce.dao.exception.DAOException;
import com.ecommerce.dao.factory.DaoFactoryDB;
import com.ecommerce.dao.util.DAOUtil;

public class DaoClient implements Dao<Client> {
	private DaoFactoryDB daoFactory;
	public DaoClient(DaoFactoryDB daoFactory) {
		this.daoFactory = daoFactory;
	}

	private static final String SQL_SELECT_BY_ID = "SELECT * FROM client WHERE id = ?";
	@Override
	public Client get(String id) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Client client = null;

		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = DAOUtil.initialisationRequetePreparee(connexion,
					SQL_SELECT_BY_ID, false, id);
			resultSet = preparedStatement.executeQuery();
			/*
			 * Parcours de la ligne de données de l'éventuel ResulSet retourné
			 */
			if (resultSet.next()) {
				client = map(resultSet);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DAOUtil.fermeturesSilencieuses(resultSet, preparedStatement,
					connexion);
		}
		return client;
	}

	private static final String SQL_SELECT_ALL = "SELECT * FROM client";
	@Override
	public Map<String, Client> getAll() {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Client client = null;
		Map<String, Client> clients = new HashMap<>();
		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = DAOUtil.initialisationRequetePreparee(connexion,
					SQL_SELECT_ALL, false);
			resultSet = preparedStatement.executeQuery();
			/*
			 * Parcours de la ligne de données de l'éventuel ResulSet retourné
			 */
			while (resultSet.next()) {
				client = map(resultSet);
				clients.put(client.getId(), client);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DAOUtil.fermeturesSilencieuses(resultSet, preparedStatement,
					connexion);
		}
		return clients;
	}

	private static final String SQL_INSERT = "INSERT INTO client (id, nom, prenom, adresse,telephone,email,image) VALUES (?, ?, ?, ?,?,?,?)";
	@Override
	public void save(Client t) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet valeursAutoGenerees = null;

		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			t.generateId();
			if (this.get(t.getId()) == null) {
				preparedStatement = DAOUtil.initialisationRequetePreparee(
						connexion, SQL_INSERT, true, t.getId(), t.getNom(),
						t.getPrenom(), t.getAdresse(), t.getTelephone(),
						t.getEmail(), t.getImage());
				int statut = preparedStatement.executeUpdate();
				/* Analyse du statut retourné par la requête d'insertion */
				if (statut == 0) {
					throw new DAOException(
							"Échec de la création de l'utilisateur, aucune ligne ajoutée dans la table.");
				}
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DAOUtil.fermeturesSilencieuses(valeursAutoGenerees,
					preparedStatement, connexion);
		}
	}
	private static final String SQL_DELETE = "DELETE FROM client WHERE id=?";

	@Override
	public void delete(Client t) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet valeursAutoGenerees = null;

		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			preparedStatement = DAOUtil.initialisationRequetePreparee(connexion,
					SQL_DELETE, true, t.getId());
			int statut = preparedStatement.executeUpdate();
			/* Analyse du statut retourné par la requête d'insertion */
			if (statut == 0) {
				throw new DAOException(
						"Échec de la suppretion de l'utilisateur, aucune ligne retirées dans la table.");
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DAOUtil.fermeturesSilencieuses(valeursAutoGenerees,
					preparedStatement, connexion);
		}

	}

	private static Client map(ResultSet resultSet) throws SQLException {
		Client client = new Client();
		client.setNom(resultSet.getString("nom"));
		client.setPrenom(resultSet.getString("prenom"));
		client.setId(resultSet.getString("id"));
		client.setAdresse(resultSet.getString("adresse"));
		client.setEmail(resultSet.getString("nom"));
		client.setImage(resultSet.getString("image"));
		return client;
	}
}
