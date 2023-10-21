package controller;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.deeplearning4j.datasets.iterator.impl.ListDataSetIterator;

public class DataPreparation {
    private final Map<String, List<String>> dataMap;

    public DataPreparation() {
        dataMap = initializeDataMap();
    }

    public DataSetIterator generateTrainingData(int numExamples) {
        List<DataSet> trainingData = new ArrayList<>();

        for (int i = 0; i < numExamples; i++) {
            // Escolha aleatoriamente um diagnóstico disponível
            String randomDiagnosis = getRandomDiagnosis();

            // Gere sintomas com base no diagnóstico escolhido
            List<String> symptoms = dataMap.get(randomDiagnosis);

            // Crie um vetor de entrada binário com base nos sintomas
            INDArray input = createBinaryInputVector(symptoms);

            // Crie um vetor de saída com base no diagnóstico escolhido
            INDArray output = createOutputVector(randomDiagnosis);

            trainingData.add(new DataSet(input, output));
        }

        return new ListDataSetIterator(trainingData, numExamples);
    }

    private Map<String, List<String>> initializeDataMap() {
        // Preencha o mapeamento de diagnósticos para sintomas aqui
        // Como na classe DataPreparation original
        // Exemplo fictício:
        Map<String, List<String>> dadosTreinamento = new HashMap();
        dadosTreinamento.put("Influenza", List.of("Febre", "Tosse", "Dor de Garganta"));
        dadosTreinamento.put("Hipertensão Arterial", List.of("Pressão Alta"));
        dadosTreinamento.put("Diabetes Mellitus", List.of("Fadiga", "Náusea"));
        dadosTreinamento.put("Câncer de Pulmão", List.of("Tosse", "Dificuldade Respiratória"));
        dadosTreinamento.put("Doença de Alzheimer", List.of("Perda de Memória"));
        dadosTreinamento.put("Artrite Reumatoide", List.of("Dor nas Articulações"));
        dadosTreinamento.put("Asma", List.of("Tosse", "Dificuldade Respiratória"));
        dadosTreinamento.put("Infarto Agudo do Miocárdio", List.of("Dor no Peito", "Fadiga"));
        dadosTreinamento.put("Obesidade", List.of("Fadiga"));
        dadosTreinamento.put("Dengue", List.of("Febre", "Dor de Cabeça", "Náusea", "Vômito"));            
        return dadosTreinamento;
    }

    private String getRandomDiagnosis() {
        List<String> availableDiagnoses = new ArrayList<>(dataMap.keySet());
        int randomIndex = (int) (Math.random() * availableDiagnoses.size());
        return availableDiagnoses.get(randomIndex);
    }

    private INDArray createBinaryInputVector(List<String> symptoms) {
        int numSymptoms = symptoms.size();
        INDArray inputVector = Nd4j.zeros(1, numSymptoms);

        for (int i = 0; i < numSymptoms; i++) {
            String symptom = symptoms.get(i);
            inputVector.putScalar(i, 1.0); // Presença do sintoma
        }

        return inputVector;
    }

    private INDArray createOutputVector(String diagnosis) {
        int numDiagnoses = dataMap.size();
        INDArray outputVector = Nd4j.zeros(1, numDiagnoses);

        int index = 0;
        for (String diag : dataMap.keySet()) {
            if (diag.equals(diagnosis)) {
                outputVector.putScalar(index, 1.0); // Diagnóstico correspondente
                break;
            }
            index++;
        }

        return outputVector;
    }
}
