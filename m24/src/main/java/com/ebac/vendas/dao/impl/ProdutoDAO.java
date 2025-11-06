package com.ebac.vendas.dao.impl;

import com.ebac.vendas.dao.IProdutoDAO;
import com.ebac.vendas.domain.Produto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProdutoDAO implements IProdutoDAO {
    private List<Produto> produtos;

    public ProdutoDAO() {
        this.produtos = new ArrayList<>();
    }

    @Override
    public void salvar(Produto produto) {
        this.produtos.add(produto);
    }

    @Override
    public void atualizar(Produto produto) {
        Produto produtoExistente = buscarPorCodigo(produto.getCodigo());
        if (produtoExistente != null) {
            produtoExistente.setNome(produto.getNome());
            produtoExistente.setPreco(produto.getPreco());
        }
    }

    @Override
    public void excluir(Produto produto) {
        this.produtos.removeIf(p -> p.getCodigo().equals(produto.getCodigo()));
    }

    @Override
    public Optional<Produto> buscarPorId(Long id) {
        // Como não temos um ID para Produto, vamos retornar vazio
        return Optional.empty();
    }

    @Override
    public List<Produto> buscarTodos() {
        return new ArrayList<>(this.produtos);
    }

    @Override
    public Produto buscarPorCodigo(String codigo) {
        return this.produtos.stream()
                .filter(produto -> produto.getCodigo().equals(codigo))
                .findFirst()
                .orElse(null);
    }
}