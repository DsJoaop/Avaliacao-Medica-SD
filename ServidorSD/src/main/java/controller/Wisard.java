package controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Wisard {
    private final Map<String, Map<String, Integer>> celulasRAM;

    /**
     * Construtor da classe Wisard.
     * 
     * @param diagnosticos Uma lista de diagnósticos possíveis.
     * @param sintomas Uma lista de sintomas possíveis.
     */
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
    }

    /**
     * Método para treinar o modelo com um diagnóstico e sintomas associados.
     * 
     * @param diagnostico O diagnóstico a ser treinado.
     * @param sintomasAssociados Uma lista de sintomas associados ao diagnóstico.
     */
    public void treinar(String diagnostico, List<String> sintomasAssociados) {
        for (String sintoma : sintomasAssociados) {
            celulasRAM.get(diagnostico).put(sintoma, 1);
        }
    }

    /**
     * Método para classificar os sintomas de um paciente e prever um diagnóstico.
     * 
     * @param sintomasPaciente Uma lista de sintomas do paciente.
     * @return O diagnóstico previsto com base nos sintomas do paciente.
     */
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
