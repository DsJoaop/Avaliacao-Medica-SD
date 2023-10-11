package controll;

import model.Consulta;
import view.Interface;

import java.util.ArrayList;
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
        consulta.getSintomas().addAll(sintomasSelecionados);
        ArrayList<String> dadosServidor = realizarConsulta(consulta);
        interfaceUsuario.exibirDiagnostico(dadosServidor);
    }

    public ArrayList<String> realizarConsulta(Consulta consulta) {
        // Lógica para realizar a consulta e obter o diagnóstico
        ArrayList<String> diagnostico = new ArrayList<>();
        diagnostico.add("Não sei, tem que implementar o apriori");
        // diagnostico.add("Diagnosticado como dengue pelo médico Asdrubal");
        consulta.setDiagnosticos(diagnostico);
        return (ArrayList<String>) consulta.getDiagnosticos();
    }

    public void encerrarServidor() {
        servidor.encerrarServidor();
    }

    public void exiberInterface() {
        interfaceUsuario.setVisible(true);
    }
}
