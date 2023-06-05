package com.skilldistillery.fishing.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CatchLogTest {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private CatchLog log;
	
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
		log = em.find(CatchLog.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		log = null;
	}

	@Test
	void test_CatchLog_basic_mappings() {
		assertNotNull(log);
		assertEquals(2021, log.getDate().getYear());
		assertEquals(2.1, log.getWeight());
	}
	
	@Test
	void test_CatchLog_BodyOfWater_ManytoOne_mapping() {
		assertNotNull(log);
		assertEquals("Lake Estes", log.getWater().getName());
	}
	
	@Test
	void test_CatchLog_Fish_ManytoOne_mapping() {
		assertNotNull(log);
		assertEquals("Rainbow Trout", log.getFish().getCommonName());
	}
	
	@Test
	void test_CatchLog_TimeOfDay_ManytoOne_mapping() {
		assertNotNull(log);
		assertEquals("Morning", log.getTime().getTimeframe());
	}
	
	@Test
	void test_CatchLog_User_ManytoOne_mapping() {
		assertNotNull(log);
		assertEquals("Bugs", log.getUser().getFirstName());
	}

}
