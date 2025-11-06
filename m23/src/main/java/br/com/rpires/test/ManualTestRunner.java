package br.com.rpires.test;

import br.com.rpires.service.NameService;

import java.util.List;
import java.util.Set;

public class ManualTestRunner {
    public static void main(String[] args) {
        int failures = 0;

        // Test 1: listaSomenteMulheres_deveRetornarTrue
        List<String> t1 = List.of("Maria", "Ana", "Joana");
        NameService svc1 = new NameService();
        boolean r1 = svc1.allAreFemale(t1);
        report("listaSomenteMulheres_deveRetornarTrue", r1);
        if (!r1) failures++;

        // Test 2: listaComHomem_deveRetornarFalse
        List<String> t2 = List.of("Maria", "Carlos", "Ana");
        NameService svc2 = new NameService();
        boolean r2 = svc2.allAreFemale(t2);
        report("listaComHomem_deveRetornarFalse", !r2); // expect false -> pass when !r2
        if (r2) failures++;

        // Test 3: usandoConjuntoPersonalizado_deveFuncionar
        List<String> t3 = List.of("Luiza", "Clara");
        NameService svc3 = new NameService(Set.of("luiza", "clara"));
        boolean r3 = svc3.allAreFemale(t3);
        report("usandoConjuntoPersonalizado_deveFuncionar", r3);
        if (!r3) failures++;

        System.out.println();
        if (failures == 0) {
            System.out.println("ALL TESTS PASSED");
            System.exit(0);
        } else {
            System.out.println(failures + " TEST(S) FAILED");
            System.exit(1);
        }
    }

    private static void report(String testName, boolean passed) {
        System.out.printf("%s: %s%n", testName, passed ? "PASS" : "FAIL");
    }
}
