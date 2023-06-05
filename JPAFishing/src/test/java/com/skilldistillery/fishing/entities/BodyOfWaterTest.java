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

class BodyOfWaterTest {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private BodyOfWater water;
	
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
		water = em.find(BodyOfWater.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		water = null;
	}

	@Test
	void test_BodyOfWater_basic_mappings() {
		assertNotNull(water);
		assertEquals("Standley Lake", water.getName());
		assertEquals(5509, water.getElevationInFt());
	}
	
	@Test
	void test_BodyOfWater_Fish_ManytoMany_mapping() {
		assertNotNull(water);
		assertTrue(water.getFishies().size() > 0);
	}
	
	@Test
	void test_BodyOfWater_CatchLog_OnetoMany_mapping() {
		assertNotNull(water);
		assertTrue(water.getLogs().size() > 0);
	}

}
