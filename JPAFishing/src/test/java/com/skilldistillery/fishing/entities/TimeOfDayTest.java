package com.skilldistillery.fishing.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TimeOfDayTest {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private TimeOfDay time;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("JPAFishing");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		time = em.find(TimeOfDay.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		time = null;
	}

	@Test
	void test_TimeOfDay_basic_mappings() {
		assertNotNull(time);
		assertEquals("Dawn", time.getTimeframe());
	}
	
	@Test
	void test_TimeOfDay_CatchLog_OnetoMany_mapping() {
		assertNotNull(time);
		assertTrue(time.getLogs().size() > 0);
	}

}
