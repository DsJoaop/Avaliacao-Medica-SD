package controller;

import java.io.IOException;
import model.Paciente;


import java.util.List;
import java.util.Random;
import server.Server;

public class ControllerServer {
    private final Server servidor;
    private DataPreparation dadosTreinados;

    public ControllerServer() {
        this.servidor = new Server(this);
        dadosTreinados = new DataPreparation();
    }
    
    public void iniciarServidor(){
       servidor.iniciarServidor();
    }
    
    
    public Paciente processarSolicitacaoConsulta(Paciente paciente) throws IOException {
        //faça a chamada para o algoritmo deeplearning4j aqui
        return paciente;
    }
    
}
