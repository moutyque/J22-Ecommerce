package com.ecommerce.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.ecommerce.beans.BeanHelper;
import com.ecommerce.beans.Commande;
import com.ecommerce.dao.contract.Dao;

public class DaoCommande implements Dao<Commande> {
	private List<Commande> commandes = new ArrayList<>();

	@Override
	public Optional<Commande> get(String id) {
		return commandes.stream().filter(c -> c.getId().contentEquals(id))
				.findFirst();
	}

	@Override
	public List<Commande> getAll() {
		return commandes;
	}

	@Override
	public boolean save(Commande t) {

		if (t.getId().isEmpty())
			t.setId(BeanHelper
					.generateId(t.getDate().toString() + t.getMontant()));

		if (this.get(t.getId()).isPresent()) {// Update
			return Collections.replaceAll(commandes, this.get(t.getId()).get(),
					t);
		} else {// Create
			return commandes.add(t);
		}
	}

	@Override
	public boolean delete(Commande t) {
		return commandes.remove(t);
	}

}
