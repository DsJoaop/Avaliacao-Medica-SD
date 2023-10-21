import controller.DataPreparation;
import org.deeplearning4j.datasets.iterator.impl.ListDataSetIterator;
import org.deeplearning4j.eval.Evaluation;
import org.deeplearning4j.nn.api.OptimizationAlgorithm;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.lossfunctions.LossFunctions;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import java.util.List;
import java.util.Map;

public class DiagnosisModel {
    private MultiLayerNetwork model;
    private Map<String, Integer> diagnosisToIndex;

    public DiagnosisModel(Map<String, Integer> diagnosisToIndex, int numInputFeatures, int numOutputClasses) {
        this.diagnosisToIndex = diagnosisToIndex;

        MultiLayerConfiguration config = new NeuralNetConfiguration.Builder()
                .seed(123)
                .updater(TrainRate)
                .optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT)
                .l2(1e-4)
                .list()
                .layer(0, new DenseLayer.Builder()
                        .nIn(numInputFeatures)
                        .nOut(64)
                        .activation(Activation.RELU)
                        .weightInit(WeightInit.XAVIER)
                        .build())
                .layer(1, new OutputLayer.Builder(LossFunctions.LossFunction.NEGATIVELOGLIKELIHOOD)
                        .nIn(64)
                        .nOut(numOutputClasses)
                        .activation(Activation.SOFTMAX)
                        .weightInit(WeightInit.XAVIER)
                        .build())
                .pretrain(false)
                .backprop(true)
                .build();

        model = new MultiLayerNetwork(config);
        model.init();
    }

    public void trainModel(DataSetIterator trainData, int numEpochs) {
        for (int epoch = 0; epoch < numEpochs; epoch++) {
            model.fit(trainData);
        }
    }

    public String diagnosePatient(List<String> symptoms) {
        INDArray input = createBinaryInputVector(symptoms);
        INDArray output = model.output(input);
        int predictedClassIndex = output.argMax(1).getInt(0);
        return getDiagnosisFromIndex(predictedClassIndex);
    }

    private INDArray createBinaryInputVector(List<String> symptoms) {
        // Use a classe DataPreparation para criar o vetor de entrada binário com base nos sintomas
        DataPreparation dataPreparation = new DataPreparation();
        return dataPreparation.createBinaryInputVector(symptoms);
    }

    private String getDiagnosisFromIndex(int index) {
        for (Map.Entry<String, Integer> entry : diagnosisToIndex.entrySet()) {
            if (entry.getValue() == index) {
                return entry.getKey();
            }
        }
        return "Diagnóstico Desconhecido";
    }

    public static void main(String[] args) {
        // Suponha que você já tenha um mapeamento diagnosisToIndex e um conjunto de treinamento trainData
        // Crie uma instância do modelo
        DiagnosisModel model = new DiagnosisModel(diagnosisToIndex, numInputFeatures, numOutputClasses);

        // Treine o modelo
        int numEpochs = 50;
        model.trainModel(trainData, numEpochs);

        // Faça uma previsão
        List<String> patientSymptoms = /* Sintomas do paciente */;
        String diagnosis = model.diagnosePatient(patientSymptoms);
        System.out.println("Diagnóstico: " + diagnosis);
    }
}
