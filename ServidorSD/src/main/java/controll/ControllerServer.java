package controll;

import model.Paciente;


import java.util.List;
import java.util.Random;
import server.Server;

public class ControllerServer {
    Server servidor;

    public ControllerServer() {
        this.servidor = new Server(this);
    }
    
    public void iniciarServidor(){
       servidor.iniciarServidor();
    }
    
    public String realizarConsulta(Paciente paciente) {
        List<String> diagnosticosDisponiveis = servidor.getDiagnosticosDisponiveis();
        // Lógica para escolher um diagnóstico aleatório
        String diagnosticoEscolhido = escolherDiagnosticoAleatorio(diagnosticosDisponiveis);
        paciente.setDiagnosticos(diagnosticoEscolhido);
        return paciente.getDiagnosticos();
    }
    
    private String escolherDiagnosticoAleatorio(List<String> diagnosticos) {
        Random random = new Random();
        int indiceAleatorio = random.nextInt(diagnosticos.size());
        return diagnosticos.get(indiceAleatorio);
    }
    
    public void encerrarServidor() {
        servidor.encerrarServidor();
    }
}
