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
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    private static final int PORTA = 2000;
    private Controller controlador;
    private ServerSocket serverSocket;

    public Server(Controller controlador) {
        this.controlador = controlador;
    }

    public void iniciarServidor() {
        try {
            serverSocket = new ServerSocket(PORTA);
            System.out.println("Servidor esperando conexões na porta: " + PORTA);
            while (true) {
                esperarConexaoEProcessar();
            }
        } catch (BindException e) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, e);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void esperarConexaoEProcessar() {
        try (Socket socket = serverSocket.accept();
             ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
             ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream())) {

            System.out.println("Cliente conectado: " + socket.getInetAddress());

            Consulta sintomas = (Consulta) inputStream.readObject();
            System.out.println("Recebi do cliente os sintomas:" + sintomas);

            ArrayList<String> diagnosticoApriori = controlador.realizarConsulta(sintomas);

            outputStream.writeObject(diagnosticoApriori);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
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
}
