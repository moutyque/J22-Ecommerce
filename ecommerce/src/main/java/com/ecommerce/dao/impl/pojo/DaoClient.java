package com.ecommerce.dao.impl.pojo;

import java.util.HashMap;
import java.util.Map;

import com.ecommerce.beans.Client;
import com.ecommerce.dao.contract.Dao;

public class DaoClient implements Dao<Client> {
	private Map<String, Client> clients = new HashMap<>();

	public Client get(String id) {
		return clients.get(id);
	}

	public Map<String, Client> getAll() {
		return clients;
	}

	public void save(Client t) {

		t.generateId();

		clients.put(t.getId(), t);

	}

	public void delete(Client t) {
		clients.remove(t.getId());

	}

}
