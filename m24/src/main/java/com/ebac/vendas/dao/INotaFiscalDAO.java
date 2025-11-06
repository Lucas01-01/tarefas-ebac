package com.ebac.vendas.dao;

import com.ebac.vendas.domain.NotaFiscal;

import java.time.LocalDate;
import java.util.List;

public interface INotaFiscalDAO extends IGenericDAO<NotaFiscal> {
    List<NotaFiscal> buscarPorData(LocalDate data);
    List<NotaFiscal> buscarPorCliente(String cpfCliente);
}