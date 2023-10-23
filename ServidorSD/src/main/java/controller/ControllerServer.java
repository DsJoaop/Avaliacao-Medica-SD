package controller;

import java.io.IOException;
import model.Paciente;

import server.Server;


public class ControllerServer {
    private final Server servidor;
    private final Wisard wisard;
    
    public ControllerServer() {
        this.servidor = new Server(this);
        this.wisard = new Wisard(servidor.getDiagnosticosDisponiveis(), servidor.getSintomasDisponiveis());
    }
    
    public void iniciarServidor(){
       servidor.iniciarServidor();
    }
    
    
    public Paciente processarSolicitacaoConsulta(Paciente paciente) throws IOException {
        String predictedDiagnosis = wisard.classificar(paciente.getSintomas());
        paciente.setDiagnostico(predictedDiagnosis);
        return paciente;
    }

    public static void main(String[] args) {
        ControllerServer controlador = new ControllerServer();
        controlador.iniciarServidor();
    }
}
