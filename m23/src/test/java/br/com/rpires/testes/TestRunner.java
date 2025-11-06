package br.com.rpires.testes;

import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;

public class TestRunner {
    public static void main(String[] args) {
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
            .selectors(DiscoverySelectors.selectClass(FemaleNamesTest.class))
            .build();
        
        Launcher launcher = LauncherFactory.create();
        SummaryGeneratingListener listener = new SummaryGeneratingListener();
        launcher.registerTestExecutionListeners(listener);
        
        System.out.println("Executando testes...\n");
        launcher.execute(request);
        
        TestExecutionSummary summary = listener.getSummary();
        System.out.println("\nResumo dos Testes:");
        System.out.println("Testes executados: " + summary.getTestsFoundCount());
        System.out.println("Testes com sucesso: " + summary.getTestsSucceededCount());
        System.out.println("Testes falhos: " + summary.getTestsFailedCount());
        System.out.println("Tempo total: " + summary.getTimeFinished() + "ms");
        
        if (summary.getTestsFailedCount() > 0) {
            System.out.println("\nDetalhes das falhas:");
            summary.getFailures().forEach(failure -> {
                System.out.println("\nTeste: " + failure.getTestIdentifier().getDisplayName());
                System.out.println("Erro: " + failure.getException().getMessage());
            });
            System.exit(1);
        }
    }
}