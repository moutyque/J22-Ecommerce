package com.ecommerce.dao.impl.db;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.Random;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.ecommerce.beans.Client;
import com.ecommerce.beans.Commande;
import com.ecommerce.dao.contract.Dao;
import com.ecommerce.dao.factory.DAOFactory;
import com.ecommerce.dao.factory.DaoFactoryDB;

public class TestCommandeCreation {
	private DAOFactory daoFacto;
	private Dao<Commande> daoCommande;
	@Before
	public void setUp() {
		daoFacto = DaoFactoryDB.getInstance();
		daoCommande = daoFacto.getDaoCommande();

		Client client = new Client();
		client.setNom("MARTY");
		client.setPrenom("Quentin");
		client.setAdresse("1 avenue du generalle de gaulle");
		client.setEmail("q.m@g.c");
		client.setTelephone("0123456789");
		client.setImage("");

		daoFacto.getDaoClient().save(client);
	}
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();

	@Test
	public void testSaveCommandeNewClient() {
		Random rand = new Random();
		int next = rand.nextInt();
		Client client = new Client();
		client.setNom("MARTY" + next);
		client.setPrenom("Quentin");
		client.setAdresse("1 avenue du generalle de gaulle");
		client.setEmail("q.m@g.c");
		client.setTelephone("0123456789");
		client.setImage("");

		Commande commande = new Commande();
		commande.setClient(client);
		commande.setDate(LocalDate.now());
		commande.setModeLivraison("Post");
		commande.setModePaiment("Cheque");
		commande.setMontant(rand.nextFloat());
		commande.setStatutLivraison("Livré");
		commande.setStatutPaiment("Payé");
		daoCommande.save(commande);

		assertEquals(commande.getId(),
				daoCommande.get(commande.getId()).getId());
	}

	@Test
	public void testSaveCommandeExistingClient() {
		Random rand = new Random();
		int next = rand.nextInt();
		Client client = daoFacto.getDaoClient().get(
				"a7f739e48229ba4e700d49cb3529cc305791ebcdd66016daa85d5423cb69cdf3");

		Commande commande = new Commande();
		commande.setClient(client);
		commande.setDate(LocalDate.now());
		commande.setModeLivraison("Post");
		commande.setModePaiment("Cheque");
		commande.setMontant(rand.nextFloat());
		commande.setStatutLivraison("Livré");
		commande.setStatutPaiment("Payé");
		daoCommande.save(commande);

		assertEquals(commande.getId(),
				daoCommande.get(commande.getId()).getId());
	}

	@Test
	public void deleteCommande() {
		Random rand = new Random();
		int next = rand.nextInt();
		Client client = new Client();
		client.setNom("MARTY" + next);
		client.setPrenom("Quentin");
		client.setAdresse("1 avenue du generalle de gaulle");
		client.setEmail("q.m@g.c");
		client.setTelephone("0123456789");
		client.setImage("");

		Commande commande = new Commande();
		commande.setClient(client);
		commande.setDate(LocalDate.now());
		commande.setModeLivraison("Post");
		commande.setModePaiment("Cheque");
		commande.setMontant(rand.nextFloat());
		commande.setStatutLivraison("Livré");
		commande.setStatutPaiment("Payé");

		int count = daoCommande.getAll().size();
		daoCommande.save(commande);
		assertEquals(commande.getId(),
				daoCommande.get(commande.getId()).getId());
		assertEquals(count + 1, daoCommande.getAll().size());
		daoCommande.delete(commande);
		assertEquals(count, daoCommande.getAll().size());
	}

}
