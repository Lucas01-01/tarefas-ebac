package com.ebac.vendas.domain;

public class ItemNota {
    private Produto produto;
    private Integer quantidade;
    private Double subtotal;

    public ItemNota(Produto produto, Integer quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
        calcularSubtotal();
    }

    private void calcularSubtotal() {
        this.subtotal = this.produto.getPreco() * this.quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
        calcularSubtotal();
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
        calcularSubtotal();
    }

    public Double getSubtotal() {
        return subtotal;
    }

    @Override
    public String toString() {
        return "ItemNota{" +
                "produto=" + produto +
                ", quantidade=" + quantidade +
                ", subtotal=" + subtotal +
                '}';
    }
}