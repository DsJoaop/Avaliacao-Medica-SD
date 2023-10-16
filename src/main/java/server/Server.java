package server;

import controll.Controller;
import model.Consulta;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    private static final int PORTA = 2000;
    private final Controller controlador;
    private ServerSocket serverSocket;
    private final ExecutorService executorService;
    private final ArrayList<String> diagnosticosDisponiveis = new ArrayList<>();


    public Server(Controller controlador) {
        this.controlador = controlador;
        // Número máximo de threads a serem criadas de forma concorrente
        this.executorService = Executors.newFixedThreadPool(10); 
        this.diagnosticosDisponiveis.addAll(Arrays.asList(
            "Influenza",
            "Hipertensão Arterial",
            "Diabetes Mellitus",
            "Câncer de Pulmão",
            "Doença de Alzheimer",
            "Artrite Reumatoide",
            "Asma",
            "Infarto Agudo do Miocárdio",
            "Obesidade",
            "Dengue"
        ));
    }

    public void iniciarServidor() {
        try {
            serverSocket = new ServerSocket(PORTA);
            System.out.println("Servidor esperando conexões na porta: " + PORTA);

            while (true) {
                Socket socket = serverSocket.accept();
                executorService.submit(() -> processarConexao(socket));
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

            Consulta sintomas = (Consulta) inputStream.readObject();
            System.out.println("Recebi do cliente os sintomas:" + sintomas);

            ArrayList<String> diagnosticoApriori = controlador.realizarConsulta(sintomas);

            outputStream.writeObject(diagnosticoApriori);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    public void encerrarServidor() {
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }

            executorService.shutdown(); // Encerra as threads quando o servidor é fechado
        } catch (IOException e) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public ArrayList<String> getDiagnosticosDisponiveis() {
        return diagnosticosDisponiveis;
    }
    
}
