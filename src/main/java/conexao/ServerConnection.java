/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexao;

/**
 *
 * @author Usuario
 */

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

public class ServerConnection {

    private static final int PORT = 2000; // Use a porta desejada para a comunicação com o servidor

    public static ArrayList<String> enviarConsulta(ArrayList<String> sintomasSelecionados, String nomeMedico) {
        try {
            InetAddress endereco = InetAddress.getLocalHost();
            Socket socket = new Socket(endereco, PORT);

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

            sintomasSelecionados.add(nomeMedico);
            System.out.println("Sintomas enviados para o servidor: " + sintomasSelecionados);

            Paciente consulta = new Paciente(sintomasSelecionados);
            objectOutputStream.writeObject(consulta);

            ArrayList<String> dadosServidor = (ArrayList<String>) objectInputStream.readObject();

            socket.close(); // fechar o socket após a comunicação

            return dadosServidor;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao interagir com o servidor: " + e.getMessage());
            return null;
        }
    }
}

