package controller;

import java.io.IOException;
import java.util.List;
import model.Paciente;

import server.Server;


public class ControllerServer {
    private final Server servidor;
    private final Wisard wisard;
    
    public ControllerServer() {
        this.servidor = new Server(this);
        this.wisard = new Wisard(servidor.getDiagnosticosDisponiveis(), servidor.getSintomasDisponiveis());
        treinarWisard();
    }
    
    public void iniciarServidor(){
       servidor.iniciarServidor();
    }
    
    
    public Paciente processarSolicitacaoConsulta(Paciente paciente) throws IOException {
        String predictedDiagnosis = wisard.classificar(paciente.getSintomas());
        paciente.setDiagnostico(predictedDiagnosis);
        return paciente;
    }
    
    private void treinarWisard(){
        wisard.treinar("Influenza", List.of("Febre", "Tosse", "Dor de Garganta"));
        wisard.treinar("Hipertensão Arterial", List.of("Pressão Alta"));
        wisard.treinar("Diabetes Mellitus", List.of("Fadiga", "Náusea"));
        wisard.treinar("Câncer de Pulmão", List.of("Tosse", "Dificuldade Respiratória"));
        wisard.treinar("Doença de Alzheimer", List.of("Perda de Memória"));
        wisard.treinar("Artrite Reumatoide", List.of("Dor nas Articulações"));
        wisard.treinar("Asma", List.of("Tosse", "Dificuldade Respiratória"));
        wisard.treinar("Infarto Agudo do Miocárdio", List.of("Dor no Peito", "Fadiga"));
        wisard.treinar("Obesidade", List.of("Fadiga"));
        wisard.treinar("Dengue", List.of("Febre", "Dor de Cabeça", "Náusea", "Vômito"));
    }
}
