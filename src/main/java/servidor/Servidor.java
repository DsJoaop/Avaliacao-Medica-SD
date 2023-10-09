package servidor;

import Interface.Interface;
import conexao.Paciente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {

    private static final int PORTA = 2000;

    public static void main(String[] args) {
        try {
            iniciarServidor();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void iniciarServidor() throws IOException, ClassNotFoundException {
        try (ServerSocket serverSocket = new ServerSocket(PORTA)) {
            System.out.println("Servidor esperando conexões na porta: " + PORTA);
            new Interface().setVisible(true);

            while (true) {
                esperarConexaoEProcessar(serverSocket);
            }
        } catch (BindException e) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private static void esperarConexaoEProcessar(ServerSocket serverSocket) throws IOException, ClassNotFoundException {
        try (Socket socket = serverSocket.accept();
             ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
             ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream())) {

            System.out.println("Cliente conectado: " + socket.getInetAddress());

            Paciente sintomas = (Paciente) inputStream.readObject();
            System.out.println("Recebi do cliente os sintomas:" + sintomas);

            ArrayList<String> diagnosticoApriori = realizarApriori(sintomas);

            outputStream.writeObject(diagnosticoApriori);
        }
    }

    private static ArrayList<String> realizarApriori(Paciente consulta) {
        ArrayList<String> diagnostico = new ArrayList<>();

        // Lógica para gerar diagnóstico a partir da consulta do paciente
        // diagnostico.add("Diagnosticado como dengue pelo médico Asdrubal");

        consulta.setDiagnosticos(diagnostico);
        return (ArrayList<String>) consulta.getDiagnosticos();
    }
}
