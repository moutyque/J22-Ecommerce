package com.ecommerce.dao;

import com.ecommerce.beans.Client;
import com.ecommerce.beans.Commande;
import com.ecommerce.dao.contract.Dao;
import com.ecommerce.dao.impl.DaoClient;
import com.ecommerce.dao.impl.DaoCommande;

public class DaoFactory {
	private static Dao<Client> daoClient = null;
	private static Dao<Commande> daoCommande = null;

	public static Dao<Client> getDaoClient() {
		if (daoClient == null)
			daoClient = new DaoClient();
		return daoClient;
	}

	public static Dao<Commande> getDaoCommande() {
		if (daoCommande == null)
			daoCommande = new DaoCommande();
		return daoCommande;
	}

}
