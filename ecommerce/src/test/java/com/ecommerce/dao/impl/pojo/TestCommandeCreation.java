package com.ecommerce.dao.impl.pojo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.junit.Test;

import com.ecommerce.beans.Client;
import com.ecommerce.beans.Commande;
import com.ecommerce.dao.impl.pojo.DaoCommande;

public class TestCommandeCreation {

	@Test
	public void testCreationClient() {

		DaoCommande dao = new DaoCommande();
		Commande c1 = new Commande();
		c1.setClient(new Client());
		c1.setDate(LocalDate.now());
		c1.setModeLivraison("UPS");
		c1.setModePaiment("CB");
		c1.setMontant(123.1);
		c1.setStatutLivraison("En cours");
		c1.setStatutPaiment("Payé");

		dao.save(c1);
		assertTrue(dao.get(c1.getId()) != null);
	}

	@Test
	public void testCreation2Commandes() {

		DaoCommande dao = new DaoCommande();
		Commande c1 = new Commande();
		c1.setClient(new Client());
		c1.setDate(LocalDate.now());
		c1.setModeLivraison("UPS");
		c1.setModePaiment("CB");
		c1.setMontant(123.1);
		c1.setStatutLivraison("En cours");
		c1.setStatutPaiment("Payé");
		dao.save(c1);

		Commande c2 = new Commande();
		c2.setClient(new Client());
		c2.setDate(LocalDate.now());
		c2.setModeLivraison("Post");
		c2.setModePaiment("Cheque");
		c2.setMontant(123.6);
		c2.setStatutLivraison("Livré");
		c2.setStatutPaiment("Payé");
		dao.save(c2);
		assertEquals(2, dao.getAll().size());
	}

	@Test
	public void updateClient() {
		DaoCommande dao = new DaoCommande();
		Commande c1 = new Commande();
		c1.setClient(new Client());
		LocalDate date1 = LocalDate.now();
		c1.setDate(date1);
		c1.setModeLivraison("UPS");
		c1.setModePaiment("CB");
		c1.setMontant(123.1);
		c1.setStatutLivraison("En cours");
		c1.setStatutPaiment("Payé");
		dao.save(c1);

		assertEquals(date1, c1.getDate());
		assertEquals("UPS", c1.getModeLivraison());
		assertEquals("CB", c1.getModePaiment());
		assertEquals(123.1, c1.getMontant(), 0);
		assertEquals("En cours", c1.getStatutLivraison());
		assertEquals("Payé", c1.getStatutPaiment());

		Commande c2 = new Commande();
		c2.setClient(new Client());
		c2.setDate(date1);
		c2.setModeLivraison("Post");
		c2.setModePaiment("Cheque");
		c2.setMontant(123.1);
		c2.setStatutLivraison("Livré");
		c2.setStatutPaiment("Payé");
		dao.save(c2);
		c1 = dao.get(c1.getId());
		assertEquals(date1, c1.getDate());
		assertEquals("Post", c1.getModeLivraison());
		assertEquals("Cheque", c1.getModePaiment());
		assertEquals(123.1, c1.getMontant(), 0);
		assertEquals("Livré", c1.getStatutLivraison());
		assertEquals("Payé", c1.getStatutPaiment());

		assertEquals(1, dao.getAll().size());
	}

	@Test
	public void testDeleteClient() {

		DaoCommande dao = new DaoCommande();
		Commande c1 = new Commande();
		c1.setClient(new Client());
		LocalDate date1 = LocalDate.now();
		c1.setDate(date1);
		c1.setModeLivraison("UPS");
		c1.setModePaiment("CB");
		c1.setMontant(123.1);
		c1.setStatutLivraison("En cours");
		c1.setStatutPaiment("Payé");
		dao.save(c1);

		Commande c2 = new Commande();
		c2.setClient(new Client());
		c2.setDate(date1.plus(2, ChronoUnit.DAYS));
		c2.setModeLivraison("Post");
		c2.setModePaiment("Cheque");
		c2.setMontant(123.6);
		c2.setStatutLivraison("Livré");
		c2.setStatutPaiment("Payé");
		dao.save(c2);

		assertEquals(2, dao.getAll().size());

		dao.delete(c2);
		assertEquals(1, dao.getAll().size());
	}
}
