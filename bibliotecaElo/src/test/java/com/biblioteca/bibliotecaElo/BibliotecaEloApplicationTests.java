package com.biblioteca.bibliotecaElo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hibernate.validator.internal.util.Contracts.assertTrue;

@SpringBootTest
class BibliotecaEloApplicationTests {

	@PersistenceContext
	private EntityManager entityManager;

	@Test
	void contextLoads() {
		boolean isConnected = printNumberOfRecords();
		assertTrue(isConnected, "Não foi possível conectar ao banco de dados");
	}

	public boolean printNumberOfRecords() {
		try {
			long count = entityManager.createQuery("SELECT COUNT(u) FROM Usuario u", Long.class).getSingleResult();
			System.out.println("A tabela 'Usuario' possui " + count + " registros.");
			return true;
		} catch (Exception e) {
			System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
			return false;
		}
	}
}





