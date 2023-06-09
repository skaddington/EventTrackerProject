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

class FishTest {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Fish fish;
	
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
		fish = em.find(Fish.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		fish = null;
	}

	@Test
	void test_Fish_basic_mappings() {
		assertNotNull(fish);
		assertEquals("Greenback Cutthroat Trout", fish.getCommonName());
	}

	@Test
	void test_Fish_BodyOfWater_ManytoMany_mapping() {
		assertNotNull(fish);
		assertTrue(fish.getWaters().size() > 0);
	}
	
	@Test
	void test_Fish_CatchLog_OnetoMany_mapping() {
		assertNotNull(fish);
		assertTrue(fish.getLogs().size() > 0);
	}
}
