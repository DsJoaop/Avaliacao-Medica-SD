package controll;

import controll.DataPreparation;
import org.deeplearning4j.datasets.iterator.impl.ListDataSetIterator;
import org.deeplearning4j.eval.Evaluation;
import org.deeplearning4j.nn.api.OptimizationAlgorithm;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.optimize.api.BaseTrainingListener;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.deeplearning4j.nn.weights.WeightInit;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.lossfunctions.LossFunctions;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.learning.config.Updater;
import org.nd4j.linalg.learning.config.Sgd;

import java.util.List;
import model.Paciente;

public class NeuralNetwork {

    public static void main(String[] args) {
        int numEpochs = 10;
        int numPatients = 100;
        int numInputFeatures = 6; // Número de sintomas
        int numOutputClasses = 10; // Número de diagnósticos possíveis
        int numHiddenNodes = 20;

        // Cria um conjunto de dados fictícios para treinamento
        List<Paciente> pacientes = DataPreparation.criarDadosDeTreinamento(numPatients);
        DataSet dataSet = createDataSet(pacientes, numInputFeatures, numOutputClasses);

        // Define a configuração da rede neural
        MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
                .seed(123)
                .optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT)
                .updater(new Sgd(0.1))
                .l2(1e-4)
                .list()
                .layer(0, new DenseLayer.Builder()
                        .nIn(numInputFeatures)
                        .nOut(numHiddenNodes)
                        .activation(Activation.RELU)
                        .weightInit(WeightInit.XAVIER)
                        .build())
                .layer(1, new OutputLayer.Builder(LossFunctions.LossFunction.NEGATIVELOGLIKELIHOOD)
                        .nIn(numHiddenNodes)
                        .nOut(numOutputClasses)
                        .activation(Activation.SOFTMAX)
                        .weightInit(WeightInit.XAVIER)
                        .build())
                .pretrain(false)
                .backprop(true)
                .build();

        MultiLayerNetwork model = new MultiLayerNetwork(conf);
        model.init();
        model.setListeners(new ScoreIterationListener(10));

        // Prepara o conjunto de dados para treinamento
        DataSetIterator trainIter = new ListDataSetIterator(dataSet.asList(), numPatients);

        // Treinamento da rede neural
        for (int i = 0; i < numEpochs; i++) {
            model.fit(trainIter);
        }

        // Avaliação do modelo (usando o mesmo conjunto de treinamento - isso não é ideal)
        Evaluation eval = model.evaluate(trainIter);
        System.out.println(eval.stats());
    }

    public static DataSet createDataSet(List<Paciente> pacientes, int numInputFeatures, int numOutputClasses) {
        int numPatients = pacientes.size();
        INDArray input = Nd4j.zeros(numPatients, numInputFeatures);
        INDArray output = Nd4j.zeros(numPatients, numOutputClasses);

        for (int i = 0; i < numPatients; i++) {
            Paciente paciente = pacientes.get(i);
            List<String> sintomas = paciente.getSintomas();
            String diagnostico = paciente.getDiagnostico();

            // Defina os valores de entrada com base nos sintomas (sintomas binários)
            for (String sintoma : sintomas) {
                int index = getIndexOfSintoma(sintoma);
                input.putScalar(i, index, 1.0);
            }

            // Defina os valores de saída com base no diagnóstico (diagnóstico one-hot)
            int outputIndex = getIndexOfDiagnostico(diagnostico);
            output.putScalar(i, outputIndex, 1.0);
        }

        return new DataSet(input, output);
    }

    public static int getIndexOfSintoma(String sintoma) {
        // Mapeie os sintomas para índices
        String[] sintomas = {
            "Febre", "Dor de Cabeça", "Tosse", "Fadiga", "Dor de Garganta", "Dificuldade Respiratória"
        };
        for (int i = 0; i < sintomas.length; i++) {
            if (sintomas[i].equals(sintoma)) {
                return i;
            }
        }
        return -1; // Sintoma não encontrado
    }

    public static int getIndexOfDiagnostico(String diagnostico) {
        // Mapeie os diagnósticos para índices
        String[] diagnosticos = {
            "Influenza", "Hipertensão Arterial", "Diabetes Mellitus", "Câncer de Pulmão", "Doença de Alzheimer",
            "Artrite Reumatoide", "Asma", "Infarto Agudo do Miocárdio", "Obesidade", "Dengue"
        };
        for (int i = 0; i < diagnosticos.length; i++) {
            if (diagnosticos[i].equals(diagnostico)) {
                return i;
            }
        }
        return -1; // Diagnóstico não encontrado
    }
}
