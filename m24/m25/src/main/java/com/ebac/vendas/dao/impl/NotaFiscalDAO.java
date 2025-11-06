package com.ebac.vendas.dao.impl;

import com.ebac.vendas.dao.INotaFiscalDAO;
import com.ebac.vendas.domain.NotaFiscal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NotaFiscalDAO implements INotaFiscalDAO {
    private List<NotaFiscal> notasFiscais;

    public NotaFiscalDAO() {
        this.notasFiscais = new ArrayList<>();
    }

    @Override
    public void salvar(NotaFiscal notaFiscal) {
        this.notasFiscais.add(notaFiscal);
    }

    @Override
    public void atualizar(NotaFiscal notaFiscal) {
        Optional<NotaFiscal> notaExistente = buscarPorId(notaFiscal.getId());
        if (notaExistente.isPresent()) {
            NotaFiscal nota = notaExistente.get();
            nota.setCliente(notaFiscal.getCliente());
            // Os itens são imutáveis após a criação da nota
        }
    }

    @Override
    public void excluir(NotaFiscal notaFiscal) {
        this.notasFiscais.removeIf(n -> n.getId().equals(notaFiscal.getId()));
    }

    @Override
    public Optional<NotaFiscal> buscarPorId(Long id) {
        return this.notasFiscais.stream()
                .filter(nota -> nota.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<NotaFiscal> buscarTodos() {
        return new ArrayList<>(this.notasFiscais);
    }

    @Override
    public List<NotaFiscal> buscarPorData(LocalDate data) {
        return this.notasFiscais.stream()
                .filter(nota -> nota.getData().equals(data))
                .toList();
    }

    @Override
    public List<NotaFiscal> buscarPorCliente(String cpfCliente) {
        return this.notasFiscais.stream()
                .filter(nota -> nota.getCliente().getCpf().equals(cpfCliente))
                .toList();
    }
}