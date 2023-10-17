package controll;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import model.DadosPaciente;
import model.Paciente;
import model.DadosServer;
import model.Requisicao;
import view.Interface;

public class ControllerPaciente {
    private final Interface interfaceUsuario;
    private static final int SERVER_PORT = 2000;
    private DadosServer resposta;

    public ControllerPaciente() {
        this.interfaceUsuario = new Interface(this);
    }

    public DadosServer FazerRequisicao(ArrayList<String> sintomasSelecionados, String nomePaciente, Requisicao tipoRequisicao) {
        try {
            // Conecte ao servidor
            Socket socket = conectarAoServidor();

            if (socket != null) {
                // Crie fluxos de saída e entrada
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

                // Crie objetos de paciente e dados do paciente
                Paciente paciente = new Paciente(sintomasSelecionados, nomePaciente);
                DadosPaciente dadosPaciente = new DadosPaciente(paciente, tipoRequisicao);

                // Envie a solicitação para o servidor (o paciente)
                objectOutputStream.writeObject(dadosPaciente);
                objectOutputStream.flush();

                // Receba a resposta do servidor
                DadosServer dadosServidor = (DadosServer) objectInputStream.readObject();
                this.resposta = dadosServidor;

                // Feche a conexão
                socket.close();

                return resposta;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Socket conectarAoServidor() {
        try {
            InetAddress endereco = InetAddress.getLocalHost();
            String nomeDoComputador = endereco.getHostName();

            endereco = InetAddress.getByName(nomeDoComputador);
            return new Socket(endereco, SERVER_PORT);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void exiberInterface() {
        interfaceUsuario.setVisible(true);
    }

    public void listarTodosDiagnosticos() {
        interfaceUsuario.listarDiagnostico(resposta.getPacientesDiagnosticados());
    }
}
