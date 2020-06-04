package com.ecommerce.dao.factory;

import com.ecommerce.dao.exception.DAOConfigurationException;
import com.ecommerce.dao.impl.hibernate.DaoClient;
import com.ecommerce.dao.impl.hibernate.DaoCommande;

public class DaoFactoryHibernate extends DAOFactory {

	public static DAOFactory getInstance() throws DAOConfigurationException {

		if (DAOFactory.daoFactory == null) {
			DAOFactory.daoFactory = new DaoFactoryHibernate();
			daoFactory.setDaoClient(new DaoClient());
			daoFactory.setDaoCommande(new DaoCommande());
		}

		return DAOFactory.daoFactory;
	}
}
