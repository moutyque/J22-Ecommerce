package com.ecommerce.dao.impl.hibernate;

import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import com.ecommerce.beans.Client;
import com.ecommerce.beans.Commande;
import com.ecommerce.dao.contract.Dao;
import com.ecommerce.dao.util.HibernateUtil;

public class DaoCommande implements Dao<Commande> {
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
	public Commande get(String id) {
		Session session = start();
		Commande commande = session.get(Commande.class, id);
		end(session);
		return commande;
	}

	@Override
	public Map<String, Commande> getAll() {
		Session session = start();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Commande> cq = cb.createQuery(Commande.class);
		Root<Commande> rootEntry = cq.from(Commande.class);
		CriteriaQuery<Commande> all = cq.select(rootEntry);

		TypedQuery<Commande> allQuery = session.createQuery(all);
		Map<String, Commande> returnMap = allQuery.getResultList().stream()
				.collect(Collectors.toMap(Commande::getId, c -> c));
		end(session);

		return returnMap;
	}

	@Override
	public void save(Commande t) {
		Session session = start();

		if (session.get(Client.class, t.getClient().getId()) == null) {
			session.save(t.getClient());
		}
		if (session.get(Commande.class, t.getId()) == null) {
			session.save(t.getClient());
			session.save(t);
		}

		end(session);

	}

	@Override
	public void delete(Commande t) {
		Session session = start();
		session.delete(t);
		end(session);

	}

}
