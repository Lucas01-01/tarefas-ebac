package br.com.rpires.testes;

import br.com.rpires.service.NameService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Female Names Service Tests")
class FemaleNamesTest {

    @Test
    @DisplayName("Should return true when all names are female")
    void listaSomenteMulheres_deveRetornarTrue() {
        List<String> names = List.of("Maria", "Ana", "Joana");
        NameService svc = new NameService();
        assertTrue(svc.allAreFemale(names));
    }

    @Test
    @DisplayName("Should return false when list contains a male name")
    void listaComHomem_deveRetornarFalse() {
        List<String> names = List.of("Maria", "Carlos", "Ana");
        NameService svc = new NameService();
        assertFalse(svc.allAreFemale(names));
    }

    @Test
    @DisplayName("Should work with custom name set")
    void usandoConjuntoPersonalizado_deveFuncionar() {
        List<String> names = List.of("Luiza", "Clara");
        NameService svc = new NameService(Set.of("luiza", "clara"));
        assertTrue(svc.allAreFemale(names));
    }

    @Test
    @DisplayName("Should handle case insensitive names")
    void deveSerCaseInsensitive() {
        List<String> names = List.of("MARIA", "ana", "JoAnA");
        NameService svc = new NameService();
        assertTrue(svc.allAreFemale(names));
    }

    @Test
    @DisplayName("Should handle whitespace in names")
    void deveLidarComEspacos() {
        List<String> names = List.of(" Maria ", "  Ana  ", "Joana  ");
        NameService svc = new NameService();
        assertTrue(svc.allAreFemale(names));
    }

    @Test
    @DisplayName("Should throw exception for null names list")
    void deveGerarErroParaListaNula() {
        NameService svc = new NameService();
        assertThrows(IllegalArgumentException.class, () -> svc.allAreFemale(null));
    }

    @Test
    @DisplayName("Should throw exception for empty names list")
    void deveGerarErroParaListaVazia() {
        NameService svc = new NameService();
        assertThrows(IllegalArgumentException.class, () -> svc.allAreFemale(List.of()));
    }

    @Test
    @DisplayName("Should throw exception for null female names set")
    void deveGerarErroParaConjuntoNulo() {
        assertThrows(IllegalArgumentException.class, () -> new NameService(null));
    }

    @Test
    @DisplayName("Should throw exception for empty female names set")
    void deveGerarErroParaConjuntoVazio() {
        assertThrows(IllegalArgumentException.class, () -> new NameService(Set.of()));
    }

    @Test
    @DisplayName("Should return unmodifiable set of known names")
    void deveRetornarConjuntoImutavel() {
        NameService svc = new NameService();
        Set<String> names = svc.getKnownFemaleNames();
        assertThrows(UnsupportedOperationException.class, () -> names.add("test"));
    }

    @Test
    @DisplayName("Should handle concurrent access safely")
    void deveLidarComAcessoConcorrente() throws Exception {
        NameService svc = new NameService();
        int numThreads = 10;
        int requestsPerThread = 1000;
        
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            List<Future<Boolean>> futures = new ArrayList<>();
            
            // Submit concurrent validation requests
            for (int i = 0; i < numThreads; i++) {
                Future<Boolean> future = executor.submit(() -> {
                    for (int j = 0; j < requestsPerThread; j++) {
                        List<String> names = List.of("Maria", "Ana", "Joana");
                        svc.allAreFemale(names);
                    }
                    return true;
                });
                futures.add(future);
            }
            
            // Verify all threads completed successfully
            for (Future<Boolean> future : futures) {
                assertTrue(future.get(), "Concurrent execution failed");
            }
        }
    }

    @Test
    @DisplayName("Should handle large lists efficiently")
    void deveLidarComListasGrandes() {
        NameService svc = new NameService();
        List<String> largeList = IntStream.range(0, 10_000)
            .mapToObj(i -> "Maria")
            .toList();
        
        long startTime = System.nanoTime();
        boolean result = svc.allAreFemale(largeList);
        long endTime = System.nanoTime();
        
        assertTrue(result);
        assertTrue((endTime - startTime) / 1_000_000 < 1000, "Processing should take less than 1 second");
    }
}