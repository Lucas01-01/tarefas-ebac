package br.com.rpires.dao;

/**
 * Interface que define o contrato para o DAO de Cliente
 */
public interface IClienteDAO {
    
    /**
     * Cadastra um novo cliente
     * @param cliente cliente a ser cadastrado
     * @return true se o cadastro foi realizado com sucesso
     */
    public Boolean cadastrar(String cliente);
    
    /**
     * Busca um cliente pelo nome
     * @param cliente nome do cliente a ser buscado
     * @return o cliente encontrado ou null se não existir
     */
    public String buscar(String cliente);
    
    /**
     * Atualiza os dados de um cliente
     * @param cliente nome do cliente a ser atualizado
     * @param novosDados novos dados do cliente
     * @return true se a atualização foi realizada com sucesso
     */
    public Boolean atualizar(String cliente, String novosDados);
    
    /**
     * Exclui um cliente
     * @param cliente nome do cliente a ser excluído
     * @return true se a exclusão foi realizada com sucesso
     */
    public Boolean excluir(String cliente);
}