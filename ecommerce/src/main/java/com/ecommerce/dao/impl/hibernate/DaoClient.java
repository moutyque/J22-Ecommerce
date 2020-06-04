package com.ecommerce.dao.impl.hibernate;

import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import com.ecommerce.beans.Client;
import com.ecommerce.dao.contract.Dao;
import com.ecommerce.dao.util.HibernateUtil;

public class DaoClient implements Dao<Client> {

	private void end(Session session) {
		session.getTransaction().commit();
		session.close();
	}

	private Session start() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		return session;
	}

	@Override
	public Client get(String id) {
		Session session = start();
		Client client = session.get(Client.class, id);
		end(session);
		return client;
	}

	@Override
	public Map<String, Client> getAll() {
		Session session = start();

		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Client> cq = cb.createQuery(Client.class);
		Root<Client> rootEntry = cq.from(Client.class);
		CriteriaQuery<Client> all = cq.select(rootEntry);

		TypedQuery<Client> allQuery = session.createQuery(all);
		Map<String, Client> returnMap = allQuery.getResultList().stream()
				.collect(Collectors.toMap(Client::getId, c -> c));
		end(session);

		return returnMap;
	}

	@Override
	public void save(Client t) {
		Session session = start();
		if (session.get(Client.class, t.getId()) == null) {
			session.save(t);
		}

		end(session);

	}

	@Override
	public void delete(Client t) {
		Session session = start();
		session.delete(t);
		end(session);

	}

}
