package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import model.Diagnostico;
import model.Paciente;

import server.Server;


public class ControllerServer {
    private final Server servidor;
    private final Wisard wisard;
    private final ArrayList<String> possiveisDiagnosticos = new ArrayList<>(Arrays.asList("Gripe",  "Artrite Reumatoide", "Asma Alérgica", "Enxaqueca Crônica","Bronquite Aguda"));
    private final ArrayList<String> sintomasDisponiveis = new ArrayList<>(Arrays.asList("Febre", "Tosse", "Coriza", "Fadiga", "Dor nas articulações", "Espirro","Falta de ar", "Dor de cabeça", "Inchaço nas articulações", "Dor no peito", "Febre"));
 
    public ControllerServer() {
        this.servidor = new Server(this);
        this.wisard = new Wisard(possiveisDiagnosticos, sintomasDisponiveis);
    }
    
    public void iniciarServidor(){
       servidor.iniciarServidor();
    }
    
    public Paciente processarSolicitacaoConsulta(Paciente paciente) throws IOException {
        String DiagnosticoPrevisto = wisard.classificar(paciente.getSintomas());
        paciente.setDiagnostico(DiagnosticoPrevisto);
        return paciente;
    }

    public String enviarDiagnostico(Diagnostico diagnostico) {
        return  wisard.treinarWisard(diagnostico.getDiagnostico(), diagnostico.getSintomas());
    }
    
    public static void main(String[] args) {
        ControllerServer controlador = new ControllerServer();
        controlador.iniciarServidor();
    }

}
