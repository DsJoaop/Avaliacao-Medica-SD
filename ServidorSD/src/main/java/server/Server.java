package server;

import controller.ControllerServer;
import model.Paciente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.DadosPaciente;
import model.DadosServer;
import controller.Requisicao;

public class Server {

    private static final int PORTA = 2000;
    private ServerSocket serverSocket;
    private final ArrayList<Paciente> pacienteDiagnosticados = new ArrayList<>();
    private final ArrayList<String> diagnosticosDisponiveis = new ArrayList<>(Arrays.asList(
            "Influenza", "Hipertensão Arterial", "Diabetes Mellitus", "Câncer de Pulmão", "Doença de Alzheimer",
            "Artrite Reumatoide", "Asma", "Infarto Agudo do Miocárdio", "Obesidade", "Dengue"
    ));
    ControllerServer controlador;

    public Server(ControllerServer controlServ) {
        this.controlador = controlServ;
    }
    

    public void iniciarServidor() {
        try {
            serverSocket = new ServerSocket(PORTA);
            System.out.println("Servidor esperando conexões na porta: " + PORTA);

            while (true) {
                Socket socket = serverSocket.accept();
                processarConexao(socket);
            }
        } catch (BindException e) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, e);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void processarConexao(Socket socket) {
        try (
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream())
        ) {
            System.out.println("Cliente conectado: " + socket.getInetAddress());

            DadosPaciente dadosPaciente = (DadosPaciente) inputStream.readObject();

            if (dadosPaciente.getRequisicao() == Requisicao.SOLICITAR_CONSULTA) { // Envie os dados do paciente
                Paciente pacienteAtendido = controlador.processarSolicitacaoConsulta(dadosPaciente.getPaciente());
                pacienteDiagnosticados.add(pacienteAtendido);
                outputStream.writeObject(pacienteAtendido); 
            } else { // Se não, envia os dados das listas do servidor
                DadosServer dadosServidor = new DadosServer(this.pacienteDiagnosticados, this.diagnosticosDisponiveis);
                outputStream.writeObject(dadosServidor); 
            }
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeSocket(socket);
        }
    }

    

    private void closeSocket(Socket socket) {
        try {
            socket.close();
        } catch (IOException e) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, e);
        }
    }


    public void encerrarServidor() {
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
        } catch (IOException e) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public ArrayList<String> getDiagnosticosDisponiveis() {
        return diagnosticosDisponiveis;
    }

    public ArrayList<Paciente> getPacienteDiagnosticados() {
        return pacienteDiagnosticados;
    }
}
