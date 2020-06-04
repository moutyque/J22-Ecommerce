package com.ecommerce.dao.factory;

import com.ecommerce.beans.Client;
import com.ecommerce.beans.Commande;
import com.ecommerce.dao.contract.Dao;
import com.ecommerce.dao.exception.DAOConfigurationException;

public abstract class DAOFactory {

	protected static DAOFactory daoFactory;

	private Dao<Client> daoClient = null;
	private Dao<Commande> daoCommande = null;
	public Dao<Client> getDaoClient() {
		return daoClient;
	}
	public void setDaoClient(Dao<Client> daoClient) {
		this.daoClient = daoClient;
	}
	public Dao<Commande> getDaoCommande() {
		return daoCommande;
	}
	public void setDaoCommande(Dao<Commande> daoCommande) {
		this.daoCommande = daoCommande;
	}

	public static DAOFactory getInstance() throws DAOConfigurationException {
		throw new DAOConfigurationException(
				"Implement the getInstance methode of DAOFactory");

	};
}
