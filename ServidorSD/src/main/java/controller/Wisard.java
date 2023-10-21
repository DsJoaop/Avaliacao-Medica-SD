package controller;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Wisard {
    private final Map<String, Map<String, Integer>> celulasRAM;

    public Wisard(List<String> diagnosticos, List<String> sintomas) {
        celulasRAM = new HashMap<>();
        for (String diagnostico : diagnosticos) {
            celulasRAM.put(diagnostico, new HashMap<>());
        }
        for (String sintoma : sintomas) {
            for (String diagnostico : diagnosticos) {
                celulasRAM.get(diagnostico).put(sintoma, 0);
            }
        }
        treinarWisard();
    }
    
    private void treinarWisard() {    
        treinar("Gripe", new ArrayList<>(Arrays.asList("Febre", "Tosse", "Coriza")));
        treinar("Hipertensão", new ArrayList<>(Arrays.asList("Fadiga", "Dor nas articulações")));
        treinar("Diabetes", new ArrayList<>(Arrays.asList("Sede excessiva", "Fadiga")));
        treinar("Asma", new ArrayList<>(Arrays.asList("Tosse", "Falta de ar")));
        treinar("Enxaqueca", new ArrayList<>(Arrays.asList("Dor de cabeça", "Náusea")));
        treinar("Artrite", new ArrayList<>(Arrays.asList("Inchaço nas articulações", "Dor nas articulações")));
        treinar("Bronquite", new ArrayList<>(Arrays.asList("Tosse", "Dificuldade respiratória", "Dor no peito")));
        treinar("Colesterol alto", new ArrayList<>(Arrays.asList("Dor no peito", "Fadiga")));
        treinar("Dengue", new ArrayList<>(Arrays.asList("Febre", "Manchas na pele")));
        treinar("Rinite", new ArrayList<>(Arrays.asList("Coriza", "Espirros","Dificuldade respiratória")));
    }

    public void treinar(String diagnostico, List<String> sintomasAssociados) {
        for (String sintoma : sintomasAssociados) {
            celulasRAM.get(diagnostico).put(sintoma, 1);
        }
    }

    public String classificar(List<String> sintomasPaciente) {
        Map<String, Integer> contagemAtivacoes = new HashMap<>();
        for (String diagnostico : celulasRAM.keySet()) {
            int contagemAtivacao = 0;
            for (String sintoma : sintomasPaciente) {
                Integer peso = celulasRAM.get(diagnostico).get(sintoma);
                if (peso != null) {
                    contagemAtivacao += peso;
                }
            }
            contagemAtivacoes.put(diagnostico, contagemAtivacao);
        }

        int maximaContagemAtivacao = -1;
        String diagnosticoPrevisto = null;
        for (Map.Entry<String, Integer> entrada : contagemAtivacoes.entrySet()) {
            if (entrada.getValue() > maximaContagemAtivacao) {
                maximaContagemAtivacao = entrada.getValue();
                diagnosticoPrevisto = entrada.getKey();
            }
        }

        return diagnosticoPrevisto;
    }
}