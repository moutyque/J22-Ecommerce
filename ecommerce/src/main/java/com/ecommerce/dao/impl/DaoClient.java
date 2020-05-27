package com.ecommerce.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.ecommerce.beans.BeanHelper;
import com.ecommerce.beans.Client;
import com.ecommerce.dao.contract.Dao;

public class DaoClient implements Dao<Client> {
	private List<Client> clients = new ArrayList<>();

	public Optional<Client> get(String id) {
		return clients.stream().filter(c -> c.getId().contentEquals(id))
				.findFirst();
	}

	public List<Client> getAll() {
		return clients;
	}

	public boolean save(Client t) {

		if (t.getId().isEmpty())
			t.setId(BeanHelper.generateId(t.getNom() + t.getPrenom()));

		if (this.get(t.getId()).isPresent()) {// Update
			return Collections.replaceAll(clients, this.get(t.getId()).get(),
					t);
		} else {// Create
			return clients.add(t);
		}

	}

	public boolean delete(Client t) {
		return clients.remove(t);

	}

}
