package com.ebac.vendas.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {
    
    @Test
    void deveCriarClienteComSucesso() {
        // Given
        String nome = "João da Silva";
        String cpf = "123.456.789-00";
        String email = "joao@email.com";

        // When
        Cliente cliente = new Cliente(nome, cpf, email);

        // Then
        assertNotNull(cliente);
        assertEquals(nome, cliente.getNome());
        assertEquals(cpf, cliente.getCpf());
        assertEquals(email, cliente.getEmail());
    }

    @Test
    void deveAlterarDadosDoClienteComSucesso() {
        // Given
        Cliente cliente = new Cliente("João", "123.456.789-00", "joao@email.com");

        // When
        cliente.setNome("João da Silva");
        cliente.setEmail("joao.silva@email.com");

        // Then
        assertEquals("João da Silva", cliente.getNome());
        assertEquals("joao.silva@email.com", cliente.getEmail());
        assertEquals("123.456.789-00", cliente.getCpf()); // CPF não deve mudar
    }

    @Test
    void deveRetornarToStringFormatadoCorretamente() {
        // Given
        Cliente cliente = new Cliente("João", "123.456.789-00", "joao@email.com");

        // When
        String toString = cliente.toString();

        // Then
        assertTrue(toString.contains("João"));
        assertTrue(toString.contains("123.456.789-00"));
        assertTrue(toString.contains("joao@email.com"));
    }
}