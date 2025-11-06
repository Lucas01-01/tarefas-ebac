package com.ebac.vendas.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class NotaFiscal {
    private Long id;
    private LocalDate data;
    private Cliente cliente;
    private List<ItemNota> itens;
    private Double valorTotal;

    public NotaFiscal(Long id, Cliente cliente) {
        this.id = id;
        this.data = LocalDate.now();
        this.cliente = cliente;
        this.itens = new ArrayList<>();
        this.valorTotal = 0.0;
    }

    public void adicionarItem(ItemNota item) {
        this.itens.add(item);
        calcularValorTotal();
    }

    private void calcularValorTotal() {
        this.valorTotal = this.itens.stream()
                .mapToDouble(ItemNota::getSubtotal)
                .sum();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemNota> getItens() {
        return itens;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    @Override
    public String toString() {
        return "NotaFiscal{" +
                "id=" + id +
                ", data=" + data +
                ", cliente=" + cliente +
                ", itens=" + itens +
                ", valorTotal=" + valorTotal +
                '}';
    }
}