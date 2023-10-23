package server;

import controller.ControllerServer;
import model.Paciente;
import model.DadosPaciente;
import model.DadosServer;
import controller.Requisicao;

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

public class Server {
    private static final int PORTA = 2000;

    private ServerSocket serverSocket;
    private final ArrayList<Paciente> pacientesDiagnosticados = new ArrayList<>();
    private final ArrayList<String> possiveisDiagnosticos = new ArrayList<>(Arrays.asList(
            "Gripe", "Resfriado Comum", "Artrite Reumatoide", "Asma Alérgica", "Enxaqueca Crônica",
            "Bronquite Aguda", "Infarto Agudo do Miocárdio", "Gripe Sazonal", "Pneumonia Viral",
            "Rinite Alérgica", "Doença Pulmonar Obstrutiva Crônica"));
    private final ArrayList<String> sintomasDisponiveis = new ArrayList<>(Arrays.asList(
            "Febre", "Tosse", "Coriza", "Fadiga", "Dor nas articulações", "Espirro",
            "Falta de ar", "Dor de cabeça", "Inchaço nas articulações", "Dor no peito", "Febre"));

    private ControllerServer controlador;

    public Server(ControllerServer controlador) {
        this.controlador = controlador;
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
            DadosPaciente dadosPaciente = (DadosPaciente) inputStream.readObject();

            if (dadosPaciente.getRequisicao() == Requisicao.SOLICITAR_CONSULTA) {
                Paciente pacienteAtendido = controlador.processarSolicitacaoConsulta(dadosPaciente.getPaciente());
                pacientesDiagnosticados.add(pacienteAtendido);
                outputStream.writeObject(pacienteAtendido);
            } else {
                DadosServer dadosServidor = new DadosServer(this.pacientesDiagnosticados, this.sintomasDisponiveis);
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

    public ArrayList<String> getDiagnosticosDisponiveis() {
        return possiveisDiagnosticos;
    }

    public ArrayList<Paciente> getPacientesDiagnosticados() {
        return pacientesDiagnosticados;
    }

    public ArrayList<String> getSintomasDisponiveis() {
        return sintomasDisponiveis;
    }
}
