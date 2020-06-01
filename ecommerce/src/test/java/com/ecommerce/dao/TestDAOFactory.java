package com.ecommerce.dao;

import static org.junit.Assert.assertEquals;

import java.nio.file.Paths;
import java.util.Random;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.ecommerce.beans.Client;
import com.ecommerce.dao.contract.Dao;
import com.ecommerce.dao.exception.DAOException;
import com.ecommerce.dao.factory.DAOFactory;
import com.ecommerce.dao.factory.DaoFactoryDB;

public class TestDAOFactory {
	private DAOFactory daoFacto;
	private Dao<Client> daoClient;
	@Before
	public void setUp() {
		daoFacto = DaoFactoryDB.getInstance();
		daoClient = daoFacto.getDaoClient();
	}
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();

	@Test
	public void testSaveClient() {

		Random rand = new Random();
		int next = rand.nextInt();
		Client c1 = new Client();
		c1.setNom("MARTY" + next);
		c1.setPrenom("Quentin");
		c1.setAdresse("1 avenue du generalle de gaulle");
		c1.setEmail("q.m@g.c");
		c1.setTelephone("0123456789");
		c1.setFichier(Paths.get(""));

		daoClient.save(c1);

		assertEquals(c1.getId(), daoClient.get(c1.getId()).getId());
	}

	@Test
	public void testSaveSameClient() {

		Client c1 = new Client();
		c1.setNom("MARTY");
		c1.setPrenom("Quentin");
		c1.setAdresse("1 avenue du generalle de gaulle");
		c1.setEmail("q.m@g.c");
		c1.setTelephone("0123456789");
		c1.setFichier(Paths.get(""));

		exceptionRule.expect(DAOException.class);
		exceptionRule.expectMessage(
				"java.sql.SQLIntegrityConstraintViolationException: Duplicate entry 'a7f739e48229ba4e700d49cb3529cc305791ebcdd66016daa85d5423cb69cdf3' for key 'client.PRIMARY'");

		daoClient.save(c1);
		daoClient.save(c1);

	}

	@Test
	public void testDeleteClient() {

		Client c1 = new Client();
		c1.setNom("MART");
		c1.setPrenom("Quentin");
		c1.setAdresse("1 avenue du generalle de gaulle");
		c1.setEmail("q.m@g.c");
		c1.setTelephone("0123456789");
		c1.setFichier(Paths.get(""));

		daoClient.save(c1);
		int initSize = daoClient.getAll().size();

		assertEquals(c1.getId(), daoClient.get(c1.getId()).getId());
		daoClient.delete(c1);
		assertEquals(initSize - 1, daoClient.getAll().size());
		exceptionRule.expect(DAOException.class);
		exceptionRule.expectMessage(
				"Échec de la suppretion de l'utilisateur, aucune ligne retirées dans la table.");
		daoClient.delete(c1);
	}
}
