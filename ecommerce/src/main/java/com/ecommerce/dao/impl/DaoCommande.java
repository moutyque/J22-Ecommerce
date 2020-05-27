package com.ecommerce.dao.impl;

import java.util.HashMap;
import java.util.Map;

import com.ecommerce.beans.BeanHelper;
import com.ecommerce.beans.Commande;
import com.ecommerce.dao.contract.Dao;

public class DaoCommande implements Dao<Commande> {
	private Map<String, Commande> commandes = new HashMap<>();

	@Override
	public Commande get(String id) {
		return commandes.get(id);
	}

	@Override
	public Map<String, Commande> getAll() {
		return commandes;
	}

	@Override
	public void save(Commande t) {

		if (t.getId().isEmpty())
			t.setId(BeanHelper
					.generateId(t.getDate().toString() + t.getMontant()));
		commandes.put(t.getId(), t);

	}

	@Override
	public void delete(Commande t) {
		commandes.remove(t.getId());
	}

}
