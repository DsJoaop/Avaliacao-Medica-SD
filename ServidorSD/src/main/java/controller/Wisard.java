package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Wisard {
    private final Map<String, Map<String, Boolean>> celulasRAM;

    // Construtor da classe Wisard
    public Wisard(List<String> diagnosticos, List<String> sintomas) {

        // Inicializa a estrutura de dados celulasRAM como um mapa aninhado
        celulasRAM = new HashMap<>();
        // Para cada diagnóstico, cria um mapa vazio dentro de celulasRAM
        for (String diagnostico : diagnosticos) {
            celulasRAM.put(diagnostico, new HashMap<>());
        }
        // Para cada diagnóstico e sintoma, inicializa a célula correspondente com valor falso
        for (String sintoma : sintomas) {
            for (String diagnostico : diagnosticos) {
                celulasRAM.get(diagnostico).put(sintoma, false);
            }
        }
        // Chama o método treinarWisard para treinar a rede
        treinarWisard();
    }

    // Método privado para treinar a rede com dados de treinamento
    private void treinarWisard() {
        // Cria um mapa de dados de treinamento com diagnósticos e sintomas associados
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

        // Itera sobre os dados de treinamento e chama o método treinar para cada um
        for (Map.Entry<String, List<String>> entry : dadosDeTreinamento.entrySet()) {
            treinar(entry.getKey(), entry.getValue());
        }
    }

    // Método para treinar a rede para um diagnóstico com sintomas associados
    public void treinar(String diagnostico, List<String> sintomasAssociados) {
        // Verifica se o diagnóstico existe na base de dados
        if (!existeDiagnostico(diagnostico)) {
            System.out.println("Diagnóstico não existe na base de dados: " + diagnostico);
        }

        // Para cada sintoma associado, ativa a célula correspondente
        for (String sintoma : sintomasAssociados) {
            if (existeSintoma(sintoma)) {
                celulasRAM.get(diagnostico).put(sintoma, true);
            } else {
                System.out.println("Aviso: O sintoma '" + sintoma + "' não existe na base de dados para o diagnóstico '" + diagnostico + "'.");
            }
        }
    }

    // Método para classificar os sintomas de um paciente e retornar um diagnóstico previsto
    public String classificar(List<String> sintomasPaciente) {
        // Filtra os sintomas válidos do paciente com base nos dados de treinamento
        List<String> sintomasValidos = filtrarSintomasValidos(sintomasPaciente);

        // Inicializa um mapa para armazenar a contagem de ativações para cada diagnóstico
        Map<String, Double> contagemAtivacoes = new HashMap<>();

        // Para cada diagnóstico, calcula a contagem de ativações
        for (String diagnostico : celulasRAM.keySet()) {
            double contagemAtivacao = 0.0;
            for (String sintoma : sintomasValidos) {
                if (celulasRAM.get(diagnostico).get(sintoma)) {
                    contagemAtivacao += 1.0;
                }
            }
            contagemAtivacoes.put(diagnostico, mapearEntrada(contagemAtivacao));
        }

        // Retorna o diagnóstico previsto com base nas contagens de ativação
        return obterDiagnosticoPrevisto(contagemAtivacoes);
    }

    // Função de mapeamento (ativação) para aplicar a função sigmoid
    public double mapearEntrada(double x) {
        return 1.0 / (1.0 + Math.exp(-x));
    }

    // Métodos privados para verificar a existência de diagnóstico e sintoma
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

    // Método para filtrar os sintomas válidos com base nos dados de treinamento
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

    // Método para obter o diagnóstico previsto com base nas contagens de ativação
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
