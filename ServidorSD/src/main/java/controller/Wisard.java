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
        treinarWisard();
    }
    
    private void treinarWisard() {    
        
        treinar("Resfriado Comum", Arrays.asList("Tosse", "Coriza", "Febre"));
        treinar("Artrite Reumatoide", Arrays.asList("Dor nas articulações", "Inchaço nas articulações"));
        treinar("Asma Alérgica", Arrays.asList("Falta de ar", "Tosse"));
        treinar("Enxaqueca Crônica", Arrays.asList("Dor de cabeça"));
        treinar("Bronquite Aguda", Arrays.asList("Tosse", "Falta de ar"));
        treinar("Infarto Agudo do Miocárdio", Arrays.asList("Dor no peito"));
        treinar("Gripe Sazonal", Arrays.asList("Tosse", "Coriza", "Febre"));
        treinar("Pneumonia Viral", Arrays.asList("Tosse", "Falta de ar"));
        treinar("Rinite Alérgica", Arrays.asList("Coriza", "Espirro"));
        treinar("Doença Pulmonar Obstrutiva Crônica", Arrays.asList("Falta de ar", "Tosse"));

        
        treinar("Resfriado Comum", Arrays.asList("Tosse", "Coriza", "Febre", "Espirro"));
        treinar("Artrite Reumatoide", Arrays.asList("Dor nas articulações", "Inchaço nas articulações", "Fadiga"));
        treinar("Asma Alérgica", Arrays.asList("Falta de ar", "Tosse", "Espirro"));
        treinar("Enxaqueca Crônica", Arrays.asList("Dor de cabeça", "Fadiga"));
        treinar("Bronquite Aguda", Arrays.asList("Tosse", "Falta de ar", "Febre"));
        treinar("Infarto Agudo do Miocárdio", Arrays.asList("Dor no peito", "Fadiga", "Febre"));
        treinar("Gripe Sazonal", Arrays.asList("Tosse", "Coriza", "Febre", "Espirro", "Fadiga"));
        treinar("Pneumonia Viral", Arrays.asList("Tosse", "Falta de ar", "Febre", "Espirro"));
        treinar("Rinite Alérgica", Arrays.asList("Coriza", "Espirro", "Fadiga"));
        treinar("Doença Pulmonar Obstrutiva Crônica", Arrays.asList("Falta de ar", "Tosse", "Fadiga"));
        
        treinar("Resfriado Comum", Arrays.asList("Tosse", "Coriza", "Febre", "Espirro"));
        treinar("Artrite Reumatoide", Arrays.asList("Dor nas articulações", "Inchaço nas articulações", "Fadiga"));
        treinar("Asma Alérgica", Arrays.asList("Falta de ar", "Tosse", "Espirro"));
        treinar("Enxaqueca Crônica", Arrays.asList("Dor de cabeça", "Fadiga"));
        treinar("Bronquite Aguda", Arrays.asList("Tosse", "Falta de ar", "Febre"));
        treinar("Infarto Agudo do Miocárdio", Arrays.asList("Dor no peito", "Fadiga", "Febre"));
        treinar("Gripe Sazonal", Arrays.asList("Tosse", "Coriza", "Febre", "Espirro", "Fadiga"));
        treinar("Pneumonia Viral", Arrays.asList("Tosse", "Falta de ar", "Febre", "Espirro"));
        treinar("Rinite Alérgica", Arrays.asList("Coriza", "Espirro", "Fadiga"));
        treinar("Doença Pulmonar Obstrutiva Crônica", Arrays.asList("Falta de ar", "Tosse", "Fadiga"));

    }

    public void treinar(String diagnostico, List<String> sintomasAssociados) {
    if (celulasRAM.containsKey(diagnostico)) {
        for (String sintoma : sintomasAssociados) {
            if (celulasRAM.get(diagnostico).containsKey(sintoma)) {
                celulasRAM.get(diagnostico).put(sintoma, true);
            } else {
                // Lidar com casos em que o sintoma não existe na base de dados
                System.out.println("Aviso: O sintoma '" + sintoma + "' não existe na base de dados.");
            }
        }
    } else {
        // Lidar com casos em que o diagnóstico não existe na base de dados
        System.out.println("Aviso: O diagnóstico '" + diagnostico + "' não existe na base de dados.");
    }
}

    public String classificar(List<String> sintomasPaciente) {
        // Certifique-se de que os sintomas existem na base de dados antes de usá-los
        List<String> sintomasValidos = new ArrayList<>();
        for (String sintoma : sintomasPaciente) {
            for (String diagnostico : celulasRAM.keySet()) {
                if (celulasRAM.get(diagnostico).containsKey(sintoma)) {
                    sintomasValidos.add(sintoma);
                    break;
                }
            }
        }

        Map<String, Double> contagemAtivacoes = new HashMap<>();
        for (String diagnostico : celulasRAM.keySet()) {
            double contagemAtivacao = 0.0;
            for (String sintoma : sintomasValidos) {
                if (celulasRAM.get(diagnostico).get(sintoma)) {
                    contagemAtivacao += 1.0; // Use uma função de ativação apropriada
                }
            }
            contagemAtivacoes.put(diagnostico, contagemAtivacao);
        }

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