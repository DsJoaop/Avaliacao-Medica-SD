package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Wisard {
    private final Map<String, Map<String, Boolean>> celulasRAM;

    public Wisard(List<String> diagnosticos, List<String> sintomas) {
        if (diagnosticos == null || diagnosticos.isEmpty()) {
            throw new IllegalArgumentException("A lista de diagnósticos não pode ser nula ou vazia.");
        }
        if (sintomas == null || sintomas.isEmpty()) {
            throw new IllegalArgumentException("A lista de sintomas não pode ser nula ou vazia.");
        }

        celulasRAM = new HashMap<>();
        for (String diagnostico : diagnosticos) {
            celulasRAM.put(diagnostico, new HashMap<>());
        }
        for (String sintoma : sintomas) {
            for (String diagnostico : diagnosticos) {
                celulasRAM.get(diagnostico).put(sintoma, false);
            }
        }
        treinarWisard();
    }

    private void treinarWisard() {
         Map<String, List<String>> dadosDeTreinamento = new HashMap<>();
        dadosDeTreinamento.put("Resfriado Comum", Arrays.asList("Tosse", "Coriza", "Febre", "Espirro"));
        dadosDeTreinamento.put("Artrite Reumatoide", Arrays.asList("Dor nas articulações", "Inchaço nas articulações", "Fadiga"));
        dadosDeTreinamento.put("Asma Alérgica", Arrays.asList("Falta de ar", "Tosse", "Espirro"));
        dadosDeTreinamento.put("Enxaqueca Crônica", Arrays.asList("Dor de cabeça", "Fadiga"));
        dadosDeTreinamento.put("Bronquite Aguda", Arrays.asList("Tosse", "Falta de ar", "Febre"));
        dadosDeTreinamento.put("Infarto Agudo do Miocárdio", Arrays.asList("Dor no peito", "Fadiga", "Febre"));
        dadosDeTreinamento.put("Gripe Sazonal", Arrays.asList("Tosse", "Coriza", "Febre", "Espirro", "Fadiga"));
        dadosDeTreinamento.put("Pneumonia Viral", Arrays.asList("Tosse", "Falta de ar", "Febre"));
        dadosDeTreinamento.put("Rinite Alérgica", Arrays.asList("Coriza", "Espirro", "Fadiga"));
        dadosDeTreinamento.put("Doença Pulmonar Obstrutiva Crônica", Arrays.asList("Falta de ar", "Tosse", "Fadiga"));

        for (Map.Entry<String, List<String>> entry : dadosDeTreinamento.entrySet()) {
            treinar(entry.getKey(), entry.getValue());
        }
    }

    public void treinar(String diagnostico, List<String> sintomasAssociados) {
        if (!existeDiagnostico(diagnostico)) {
            System.out.println("Diagnóstico não existe na base de dados: " + diagnostico);
        }

        for (String sintoma : sintomasAssociados) {
            if (existeSintoma(sintoma)) {
                celulasRAM.get(diagnostico).put(sintoma, true);
            } else {
                System.out.println("Aviso: O sintoma '" + sintoma + "' não existe na base de dados para o diagnóstico '" + diagnostico + "'.");
            }
        }
    }

    public String classificar(List<String> sintomasPaciente) {
        List<String> sintomasValidos = filtrarSintomasValidos(sintomasPaciente);

        Map<String, Double> contagemAtivacoes = new HashMap<>();
        for (String diagnostico : celulasRAM.keySet()) {
            double contagemAtivacao = 0.0;
            for (String sintoma : sintomasValidos) {
                if (celulasRAM.get(diagnostico).get(sintoma)) {
                    contagemAtivacao += 1.0;
                }
            }
            contagemAtivacoes.put(diagnostico, mapearEntrada(contagemAtivacao));
        }

        return obterDiagnosticoPrevisto(contagemAtivacoes);
    }
    
    public double mapearEntrada(double x) {
        return 1.0 / (1.0 + Math.exp(-x));
    }

    private boolean existeDiagnostico(String diagnostico) {
        return celulasRAM.containsKey(diagnostico);
    }

    private boolean existeSintoma(String sintoma) {
        for (String diagnostico : celulasRAM.keySet()) {
            if (celulasRAM.get(diagnostico).containsKey(sintoma)) {
                return true;
            }
        }
        return false;
    }

    private List<String> filtrarSintomasValidos(List<String> sintomasPaciente) {
        List<String> sintomasValidos = new ArrayList<>();
        for (String sintoma : sintomasPaciente) {
            for (String diagnostico : celulasRAM.keySet()) {
                if (celulasRAM.get(diagnostico).containsKey(sintoma)) {
                    sintomasValidos.add(sintoma);
                    break;
                }
            }
        }
        return sintomasValidos;
    }

    private String obterDiagnosticoPrevisto(Map<String, Double> contagemAtivacoes) {
        double maximaContagemAtivacao = 0.0;
        String diagnosticoPrevisto = null;
        for (Map.Entry<String, Double> entrada : contagemAtivacoes.entrySet()) {
            if (entrada.getValue() > maximaContagemAtivacao) {
                maximaContagemAtivacao = entrada.getValue();
                diagnosticoPrevisto = entrada.getKey();
            }
        }
        return diagnosticoPrevisto;
    }
}
