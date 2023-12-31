package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Wisard {
    private final Map<String, Map<String, Boolean>> celulasRAM;

    public Wisard(List<String> diagnosticos, List<String> sintomas) {
        celulasRAM = new HashMap<>();
        for (String diagnostico : diagnosticos) {
            celulasRAM.put(diagnostico, new HashMap<>());
        }
        for (String sintoma : sintomas) {
            for (String diagnostico : diagnosticos) {
                celulasRAM.get(diagnostico).put(sintoma, false);
            }
        }
        this.treinoInicial();
    }
    
    public final void treinoInicial(){
        // Treinar os 5 diagnósticos com os sintomas associados
        treinarWisard("Gripe", Arrays.asList("Febre", "Tosse", "Coriza"));
        treinarWisard("Artrite Reumatoide", Arrays.asList("Dor nas articulações", "Inchaço nas articulações"));
        treinarWisard("Asma Alérgica", Arrays.asList("Tosse", "Falta de ar"));
        treinarWisard("Enxaqueca Crônica", Arrays.asList("Dor de cabeça"));
        treinarWisard("Bronquite Aguda", Arrays.asList("Tosse", "Febre", "Falta de ar"));
    }
    
    public String treinarWisard(String diagnostico, List<String> sintomasAssociados) {
        String mensagem = "Erro ao cadastrar diagnóstico";
        if (!existeDiagnostico(diagnostico)) {
            mensagem = "Diagnóstico não existe na base de dados: " + diagnostico;
        }
        for (String sintoma : sintomasAssociados) {
            if (existeSintoma(sintoma)) {
                celulasRAM.get(diagnostico).put(sintoma, true);
                mensagem = "Diagnóstico recebido com sucesso!";
            } else {
                System.out.println("Aviso: O sintoma '" + sintoma + "' não existe na base de dados para o diagnóstico '" + diagnostico + "'.");
            }
        }
        return mensagem;
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

    private double mapearEntrada(double x) {
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
