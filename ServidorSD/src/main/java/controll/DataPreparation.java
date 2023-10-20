package controll;

import java.util.ArrayList;
import java.util.Random;
import model.Paciente;

public class DataPreparation {

    public static ArrayList<Paciente> criarDadosDeTreinamento(int numPacientes) {
        ArrayList<Paciente> pacientes = new ArrayList<>();

        for (int i = 0; i < numPacientes; i++) {
            String nome = "Paciente " + (i + 1);
            ArrayList<String> sintomas = gerarSintomasAleatorios();
            String diagnostico = gerarDiagnosticoAleatorio();
            Paciente paciente = new Paciente(sintomas, nome);
            paciente.setDiagnostico(diagnostico);
            pacientes.add(paciente);
        }

        return pacientes;
    }

    public static ArrayList<String> gerarSintomasAleatorios() {
        ArrayList<String> sintomas = new ArrayList<>();
        String[] listaDeSintomas = {
            "Febre", "Dor de Cabeça", "Fadiga", "Dor de Abdominal", "Tosse", "Vômito", "Diarreia", "Dor nas Costas", "Dor no Peito", "Perda de Peso",
        };

        Random random = new Random();
        int numSintomas = random.nextInt(6) + 1; // Gere de 1 a 6 sintomas aleatórios

        for (int i = 0; i < numSintomas; i++) {
            int indiceSintoma = random.nextInt(listaDeSintomas.length);
            sintomas.add(listaDeSintomas[indiceSintoma]);
        }

        return sintomas;
    }

    public static String gerarDiagnosticoAleatorio() {
        String[] diagnosticosDisponiveis = {
            "Diabetes", "Doença cardíaca", "lesões musculares", "intoxicação alimentar", "Infecções respiratórias",
            "Gastroenterite", "doenças autoimunes", "Enxaqueca", "Obesidade", "Infecções virais"
        };

        Random random = new Random();
        int indiceDiagnostico = random.nextInt(diagnosticosDisponiveis.length);

        return diagnosticosDisponiveis[indiceDiagnostico];
    }
}
