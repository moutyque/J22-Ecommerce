package com.ecommerce.dao.impl.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.ecommerce.beans.Client;
import com.ecommerce.beans.Commande;
import com.ecommerce.dao.contract.Dao;
import com.ecommerce.dao.exception.DAOException;
import com.ecommerce.dao.factory.DaoFactoryDB;
import com.ecommerce.dao.util.DAOUtil;

public class DaoCommande implements Dao<Commande> {
	private DaoFactoryDB daoFactory;
	public DaoCommande(DaoFactoryDB daoFactory) {
		this.daoFactory = daoFactory;
	}

	private static final String SQL_SELECT_BY_ID = "SELECT * FROM commande WHERE id = ?";

	@Override
	public Commande get(String id) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Commande commande = null;

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
				commande = map(resultSet);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DAOUtil.fermeturesSilencieuses(resultSet, preparedStatement,
					connexion);
		}
		return commande;
	}
	private static final String SQL_SELECT_ALL = "SELECT * FROM commande";

	@Override
	public Map<String, Commande> getAll() {
		Map<String, Commande> map = new HashMap<>();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Commande commande = null;

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
				commande = map(resultSet);
				map.put(commande.getId(), commande);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DAOUtil.fermeturesSilencieuses(resultSet, preparedStatement,
					connexion);
		}
		return map;
	}

	private static final String SQL_INSERT = "INSERT INTO commande (id, id_client, date, montant,mode_paiement,statut_paiement,mode_livraison,statut_livraison) VALUES (?, ?, ?, ?,?,?,?,?)";
	@Override
	public void save(Commande t) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet valeursAutoGenerees = null;

		try {
			/* Récupération d'une connexion depuis la Factory */
			connexion = daoFactory.getConnection();
			t.generateId();
			if (this.get(t.getId()) == null) {
				this.daoFactory.getDaoClient().save(t.getClient());
				preparedStatement = DAOUtil.initialisationRequetePreparee(
						connexion, SQL_INSERT, true, t.getId(),
						t.getClient().getId(), t.getDate(), t.getMontant(),
						t.getModePaiment(), t.getStatutPaiment(),
						t.getModeLivraison(), t.getStatutLivraison());
				int statut = preparedStatement.executeUpdate();
				/* Analyse du statut retourné par la requête d'insertion */
				if (statut == 0) {
					throw new DAOException(
							"Échec de la création de la commande, aucune ligne ajoutée dans la table.");
				}
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DAOUtil.fermeturesSilencieuses(valeursAutoGenerees,
					preparedStatement, connexion);
		}

	}
	private static final String SQL_DELETE = "DELETE FROM commande WHERE id=?";
	@Override
	public void delete(Commande t) {
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
						"Échec de la suppretion de la commande, aucune ligne retirées dans la table.");
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			DAOUtil.fermeturesSilencieuses(valeursAutoGenerees,
					preparedStatement, connexion);
		}

	}
	private Commande map(ResultSet resultSet) throws SQLException {

		Commande commande = new Commande();
		commande.setDate(resultSet.getDate("date").toLocalDate());
		commande.setId(resultSet.getString("id"));
		commande.setModeLivraison(resultSet.getString("mode_livraison"));
		commande.setModePaiment(resultSet.getString("mode_paiement"));
		commande.setMontant(resultSet.getDouble("montant"));
		commande.setStatutLivraison(resultSet.getString("statut_livraison"));
		commande.setStatutPaiment(resultSet.getString("statut_paiement"));

		String clientId = resultSet.getString("id_client");
		if (clientId != null && !clientId.isEmpty()) {
			commande.setClient(this.daoFactory.getDaoClient().get(clientId));
		} else {
			commande.setClient(new Client());
		}

		return commande;
	}
}
