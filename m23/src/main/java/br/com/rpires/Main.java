package br.com.rpires;

import br.com.rpires.service.NameService;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        // Teste com a lista padrão de nomes
        NameService service = new NameService();
        
        System.out.println("=== Testes Básicos ===");
        
        // Teste 1: Lista apenas com nomes femininos
        List<String> listaMulheres = List.of("Maria", "Ana", "Joana");
        System.out.println("Teste 1 - Lista só de mulheres: " + 
            (service.allAreFemale(listaMulheres) ? "PASSOU" : "FALHOU"));
        
        // Teste 2: Lista com nome masculino
        List<String> listaComHomem = List.of("Maria", "Carlos", "Ana");
        System.out.println("Teste 2 - Lista com homem: " + 
            (!service.allAreFemale(listaComHomem) ? "PASSOU" : "FALHOU"));
        
        // Teste 3: Teste com espaços e case insensitive
        List<String> listaCaseInsensitive = List.of(" MARIA ", "  ana  ", "JoAnA");
        System.out.println("Teste 3 - Case insensitive e espaços: " + 
            (service.allAreFemale(listaCaseInsensitive) ? "PASSOU" : "FALHOU"));
        
        System.out.println("\n=== Testes de Validação ===");
        
        // Teste 4: Lista nula
        try {
            service.allAreFemale(null);
            System.out.println("Teste 4 - Lista nula: FALHOU (deveria ter lançado exceção)");
        } catch (IllegalArgumentException e) {
            System.out.println("Teste 4 - Lista nula: PASSOU");
        }
        
        // Teste 5: Lista vazia
        try {
            service.allAreFemale(List.of());
            System.out.println("Teste 5 - Lista vazia: FALHOU (deveria ter lançado exceção)");
        } catch (IllegalArgumentException e) {
            System.out.println("Teste 5 - Lista vazia: PASSOU");
        }
        
        // Teste 6: Conjunto personalizado
        System.out.println("\n=== Teste com Conjunto Personalizado ===");
        NameService servicoPersonalizado = new NameService(Set.of("luiza", "clara"));
        List<String> listaPersonalizada = List.of("Luiza", "Clara");
        System.out.println("Teste 6 - Conjunto personalizado: " + 
            (servicoPersonalizado.allAreFemale(listaPersonalizada) ? "PASSOU" : "FALHOU"));
        
        // Teste 7: Conjunto imutável
        System.out.println("\n=== Teste de Imutabilidade ===");
        try {
            Set<String> nomes = service.getKnownFemaleNames();
            nomes.add("teste");
            System.out.println("Teste 7 - Imutabilidade: FALHOU (deveria ter lançado exceção)");
        } catch (UnsupportedOperationException e) {
            System.out.println("Teste 7 - Imutabilidade: PASSOU");
        }
    }
}