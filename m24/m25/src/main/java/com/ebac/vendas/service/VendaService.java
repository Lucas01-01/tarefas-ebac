package com.ebac.vendas.service;

import com.ebac.vendas.dao.INotaFiscalDAO;
import com.ebac.vendas.domain.ItemNota;
import com.ebac.vendas.domain.NotaFiscal;
import com.ebac.vendas.domain.Cliente;

import java.time.LocalDate;
import java.util.List;

public class VendaService {
    private INotaFiscalDAO notaFiscalDAO;
    private static Long proximoId = 1L;

    public VendaService(INotaFiscalDAO notaFiscalDAO) {
        this.notaFiscalDAO = notaFiscalDAO;
    }

    public NotaFiscal criarVenda(Cliente cliente, List<ItemNota> itens) {
        NotaFiscal notaFiscal = new NotaFiscal(gerarProximoId(), cliente);
        
        for (ItemNota item : itens) {
            notaFiscal.adicionarItem(item);
        }

        notaFiscalDAO.salvar(notaFiscal);
        return notaFiscal;
    }

    public List<NotaFiscal> buscarVendasPorData(LocalDate data) {
        return notaFiscalDAO.buscarPorData(data);
    }

    public List<NotaFiscal> buscarVendasPorCliente(String cpfCliente) {
        return notaFiscalDAO.buscarPorCliente(cpfCliente);
    }

    private synchronized Long gerarProximoId() {
        return proximoId++;
    }
}