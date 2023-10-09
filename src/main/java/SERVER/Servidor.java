package SERVER;

import UI.Interface;
import CONECT.Paciente;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor implements Serializable {

    private static final int PORTA = 2000;
    private static ArrayList<Paciente> paciente = new ArrayList<>();
    private static ArrayList<String> diagnosticoApriori;

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ServerSocket serverSocket;

        try {
            serverSocket = new ServerSocket(PORTA);
            System.out.println("Servidor esperando conexões na porta: " + PORTA);
        } catch (BindException e) {
            e.printStackTrace();
            return;
        }
        
        new Interface().setVisible(true);
        
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Cliente conectado: " + socket.getInetAddress());

            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            Paciente sintomas = (Paciente) inputStream.readObject();
            
            System.out.println("Recebi do cliente os sintomas:" + sintomas );

            paciente.add(sintomas);

            diagnosticoApriori = realizarApriori(paciente);

            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

            outputStream.writeObject(diagnosticoApriori);

            socket.close();
        }

    }

    public static int getPORTA() {
        return PORTA;
    }

    public static ArrayList<String> getDiagnosticoAutomatico() {
        return diagnosticoApriori;
    }

    private static ArrayList<String> realizarApriori(ArrayList<Paciente> consultas) {
        ArrayList<String> diagnostico = new ArrayList();
        ArrayList<String> sintomas = new ArrayList();

        //diagnostico.add("Diagnosticado como dengue pelo médico Asdrubal");

        consultas.get(0).setDiagnosticos(diagnostico);
        diagnosticoApriori = (ArrayList<String>) consultas.get(0).getDiagnosticos();
        return diagnosticoApriori;
    }
}
