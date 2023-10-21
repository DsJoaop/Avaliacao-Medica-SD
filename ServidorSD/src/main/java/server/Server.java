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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.DadosPaciente;
import model.DadosServer;
import controller.Requisicao;

public class Server {

    private static final int PORTA = 2000;
    private ServerSocket serverSocket;
    private final ArrayList<Paciente> pacienteDiagnosticados = new ArrayList<>();
    // ArrayList de diagnósticos
    ArrayList<String> possiveisDiagnosticos = new ArrayList<>(Arrays.asList("Gripe", "Hipertensão", "Diabetes", "Asma", "Enxaqueca", "Artrite", "Bronquite", "Colesterol alto", "Dengue", "Rinite"));
        // ArrayList de sintomas
    ArrayList<String> sintomasDisponiveis = new ArrayList<>(Arrays.asList("Febre", "Tosse", "Sede excessiva", "Fadiga", "Dor de cabeça", "Dor nas articulações", "Coriza", "Falta de ar", "Náusea", "Inchaço nas articulações"));
    private ControllerServer controlador;

    public Server(ControllerServer controlServ) {
        this.controlador = controlServ;
    }

    public void iniciarServidor() {
        try {
            serverSocket = new ServerSocket(PORTA);
            System.out.println("Servidor esperando conexões na porta: " + PORTA);

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
        try (
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream())
        ) {
            System.out.println("Cliente conectado: " + socket.getInetAddress());

            DadosPaciente dadosPaciente = (DadosPaciente) inputStream.readObject();

            if (dadosPaciente.getRequisicao() == Requisicao.SOLICITAR_CONSULTA) {
                Paciente pacienteAtendido = controlador.processarSolicitacaoConsulta(dadosPaciente.getPaciente());
                pacienteDiagnosticados.add(pacienteAtendido);
                outputStream.writeObject(pacienteAtendido);
            } else {
                DadosServer dadosServidor = new DadosServer(this.pacienteDiagnosticados, this.sintomasDisponiveis);
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

    public List<String> getDiagnosticosDisponiveis() {
        return possiveisDiagnosticos;
    }

    public List<Paciente> getPacientesDiagnosticados() {
        return pacienteDiagnosticados;
    }

    public List<String> getSintomasDisponiveis() {
        return sintomasDisponiveis;
    }
}
