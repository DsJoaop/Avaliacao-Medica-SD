package server;

import controller.ControllerServer;
import model.Paciente;
import model.DadosInterface;
import model.Diagnostico;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {
    private static final int PORTA = 2000;
    private ServerSocket serverSocket;
    private final ArrayList<Paciente> pacientesDiagnosticados;
    private final ControllerServer controlador;

    public Server(ControllerServer controlador) {
        this.controlador = controlador;
        this.pacientesDiagnosticados = new ArrayList<>();
    }

    public void iniciarServidor() {
        try {
            serverSocket = new ServerSocket(PORTA);
            System.out.println("Servidor aguardando conexões na porta: " + PORTA);

            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(() -> processarConexao(socket)).start();
            }
        } catch (BindException e) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, e);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void processarConexao(Socket socket) {
        try (ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
             ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream())) {

            System.out.println("Cliente conectado: " + socket.getInetAddress());
            DadosInterface dadosInterface = (DadosInterface) inputStream.readObject();

            switch (dadosInterface.getRequisicao()) {
                case SOLICITAR_CONSULTA -> {
                    Paciente pacienteAtendido = controlador.processarSolicitacaoConsulta(dadosInterface.getPaciente());
                    pacientesDiagnosticados.add(pacienteAtendido);
                    outputStream.writeObject(pacienteAtendido);
                }
                case SOLICITAR_DIAGNOSTICOS -> {
                    outputStream.writeObject(this.pacientesDiagnosticados);
                }
                default -> {
                    Diagnostico diagnostico = dadosInterface.getDiagnostico();
                    String mensagem = controlador.enviarDiagnostico(diagnostico);
                    outputStream.writeObject(mensagem);
                }
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

    public ArrayList<Paciente> getPacientesDiagnosticados() {
        return pacientesDiagnosticados;
    }
}
