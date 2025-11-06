package com.ebac.vendas.dao.impl;

import com.ebac.vendas.domain.Cliente;
import com.ebac.vendas.domain.NotaFiscal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class NotaFiscalDAOTest {
    
    private NotaFiscalDAO notaFiscalDAO;
    private NotaFiscal notaFiscal;
    private Cliente cliente;

    @BeforeEach
    void setUp() {
        notaFiscalDAO = new NotaFiscalDAO();
        cliente = new Cliente("João", "123.456.789-00", "joao@email.com");
        notaFiscal = new NotaFiscal(1L, cliente);
    }

    @Test
    void deveSalvarNotaFiscalComSucesso() {
        // When
        notaFiscalDAO.salvar(notaFiscal);

        // Then
        var notaEncontrada = notaFiscalDAO.buscarPorId(1L);
        assertTrue(notaEncontrada.isPresent());
        assertEquals(notaFiscal.getId(), notaEncontrada.get().getId());
    }

    @Test
    void deveAtualizarNotaFiscalComSucesso() {
        // Given
        notaFiscalDAO.salvar(notaFiscal);
        Cliente novoCliente = new Cliente("Maria", "987.654.321-00", "maria@email.com");

        // When
        notaFiscal.setCliente(novoCliente);
        notaFiscalDAO.atualizar(notaFiscal);

        // Then
        var notaAtualizada = notaFiscalDAO.buscarPorId(1L);
        assertTrue(notaAtualizada.isPresent());
        assertEquals("Maria", notaAtualizada.get().getCliente().getNome());
    }

    @Test
    void deveExcluirNotaFiscalComSucesso() {
        // Given
        notaFiscalDAO.salvar(notaFiscal);

        // When
        notaFiscalDAO.excluir(notaFiscal);

        // Then
        var notaExcluida = notaFiscalDAO.buscarPorId(1L);
        assertTrue(notaExcluida.isEmpty());
    }

    @Test
    void deveBuscarNotasFiscaisPorData() {
        // Given
        notaFiscalDAO.salvar(notaFiscal);
        LocalDate hoje = LocalDate.now();

        // When
        var notasDeHoje = notaFiscalDAO.buscarPorData(hoje);

        // Then
        assertEquals(1, notasDeHoje.size());
        assertEquals(notaFiscal.getId(), notasDeHoje.get(0).getId());
    }

    @Test
    void deveBuscarNotasFiscaisPorCliente() {
        // Given
        notaFiscalDAO.salvar(notaFiscal);

        // When
        var notasDoCliente = notaFiscalDAO.buscarPorCliente("123.456.789-00");

        // Then
        assertEquals(1, notasDoCliente.size());
        assertEquals(notaFiscal.getId(), notasDoCliente.get(0).getId());
    }

    @Test
    void deveRetornarListaVaziaQuandoNaoEncontrarNotas() {
        // When
        var notasInexistentes = notaFiscalDAO.buscarPorCliente("000.000.000-00");

        // Then
        assertTrue(notasInexistentes.isEmpty());
    }
}