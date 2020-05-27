package ecommerce.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.ecommerce.beans.Client;
import com.ecommerce.dao.impl.DaoClient;

public class TestClientCreation {

	@Test
	public void testCreationClient() {

		DaoClient dao = new DaoClient();
		Client c1 = new Client();
		c1.setNom("MARTY");
		c1.setPrenom("Quentin");
		c1.setAdresse("1 avenue du generalle de gaulle");
		c1.setEmail("q.m@g.c");
		c1.setTelephone("0123456789");

		dao.save(c1);
		assertTrue(dao.get(c1.getId()) != null);
	}

	@Test
	public void testCreation2Client() {

		DaoClient dao = new DaoClient();
		Client c1 = new Client();
		c1.setNom("MARTY");
		c1.setPrenom("Quentin");
		c1.setAdresse("1 avenue du generalle de gaulle");
		c1.setEmail("q.m@g.c");
		c1.setTelephone("0123456789");
		dao.save(c1);
		Client c2 = new Client();
		c2.setNom("MARTY");
		c2.setPrenom("Julie");
		c2.setAdresse("1 avenue du generalle de gaulle");
		c2.setEmail("j.m@g.c");
		c2.setTelephone("0123456789");
		dao.save(c2);
		assertEquals(2, dao.getAll().size());
	}

	@Test
	public void updateClient() {
		DaoClient dao = new DaoClient();
		Client c1 = new Client();
		c1.setNom("MARTY");
		c1.setPrenom("Quentin");
		c1.setAdresse("1 avenue du generalle de gaulle");
		c1.setEmail("q.m@g.c");
		c1.setTelephone("0123456789");
		dao.save(c1);

		assertEquals("MARTY", c1.getNom());
		assertEquals("Quentin", c1.getPrenom());
		assertEquals("1 avenue du generalle de gaulle", c1.getAdresse());
		assertEquals("q.m@g.c", c1.getEmail());
		assertEquals("0123456789", c1.getTelephone());

		Client c2 = new Client();
		c2.setNom("MARTY");
		c2.setPrenom("Quentin");
		c2.setAdresse("2 avenue du generalle de gaulle");
		c2.setEmail("j.m@g.c");
		c2.setTelephone("0123456780");
		dao.save(c2);

		c1 = dao.get(c1.getId());
		assertEquals("MARTY", c1.getNom());
		assertEquals("Quentin", c1.getPrenom());
		assertEquals("2 avenue du generalle de gaulle", c1.getAdresse());
		assertEquals("j.m@g.c", c1.getEmail());
		assertEquals("0123456780", c1.getTelephone());

		assertEquals(1, dao.getAll().size());
	}

	@Test
	public void testDeleteClient() {

		DaoClient dao = new DaoClient();
		Client c1 = new Client();
		c1.setNom("MARTY");
		c1.setPrenom("Quentin");
		c1.setAdresse("1 avenue du generalle de gaulle");
		c1.setEmail("q.m@g.c");
		c1.setTelephone("0123456789");
		dao.save(c1);
		Client c2 = new Client();
		c2.setNom("MARTY");
		c2.setPrenom("Julie");
		c2.setAdresse("1 avenue du generalle de gaulle");
		c2.setEmail("j.m@g.c");
		c2.setTelephone("0123456789");
		dao.save(c2);
		assertEquals(2, dao.getAll().size());

		dao.delete(c2);
		assertEquals(1, dao.getAll().size());
	}
}
