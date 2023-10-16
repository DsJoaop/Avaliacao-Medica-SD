package controll;

import model.Consulta;
import view.Interface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import server.Server;

public class Controller {

    private final Interface interfaceUsuario;
    private final Server servidor;

    public Controller() {
        this.interfaceUsuario = new Interface(this);
        this.servidor = new Server(this);
    }
    
    

    public void iniciarServidor() {
        servidor.iniciarServidor();
    }

    public void processarConsulta(ArrayList<String> sintomasSelecionados, String nomeMedico) {
        Consulta consulta = new Consulta(sintomasSelecionados);
        ArrayList<String> dadosServidor = realizarConsulta(consulta);
        interfaceUsuario.exibirDiagnostico(dadosServidor);
    }
    
    public ArrayList<String> realizarConsulta(Consulta consulta) {
       List<String> diagnosticosDisponiveis = servidor.getDiagnosticosDisponiveis();
        // Lógica para escolher um diagnóstico aleatório
        String diagnosticoEscolhido = escolherDiagnosticoAleatorio(diagnosticosDisponiveis);
        consulta.setDiagnosticos(Collections.singletonList(diagnosticoEscolhido));
        return new ArrayList<>(consulta.getDiagnosticos());
    }

    private String escolherDiagnosticoAleatorio(List<String> diagnosticos) {
        Random random = new Random();
        int indiceAleatorio = random.nextInt(diagnosticos.size());
        return diagnosticos.get(indiceAleatorio);
    }

    

    public void encerrarServidor() {
        servidor.encerrarServidor();
    }

    public void exiberInterface() {
        interfaceUsuario.setVisible(true);
    }
}
