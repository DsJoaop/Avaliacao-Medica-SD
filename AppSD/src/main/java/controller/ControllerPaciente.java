package controller;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import model.DadosPaciente;
import model.DadosServer;
import model.Paciente;
import view.Interface;

public class ControllerPaciente {
    private final Interface interfaceUsuario;
    private static final int SERVER_PORT = 2000;

    public ControllerPaciente() {
        this.interfaceUsuario = new Interface(this);
    }

    private Socket conectarAoServidor() throws IOException {
        InetAddress endereco = InetAddress.getLocalHost();
        String nomeDoComputador = endereco.getHostName();
        endereco = InetAddress.getByName(nomeDoComputador);
        return new Socket(endereco, SERVER_PORT);
    }

    private Paciente enviarSolicitacaoConsulta(DadosPaciente dadosPaciente) {
        try (
            Socket socket = conectarAoServidor();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream())
        ) {
            objectOutputStream.writeObject(dadosPaciente);
            objectOutputStream.flush();
            return (Paciente) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private DadosServer enviarSolicitacaoDadosServidor(DadosPaciente dadosRecebidos) {
        try (
            Socket socket = conectarAoServidor();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream())
        ) {
            objectOutputStream.writeObject(dadosRecebidos);
            objectOutputStream.flush();
            return (DadosServer) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Paciente requisitarConsulta(ArrayList<String> sintomasSelecionados, String nomePaciente) {
        Paciente paciente = new Paciente(sintomasSelecionados, nomePaciente);
        DadosPaciente dadosPaciente = new DadosPaciente(paciente, Requisicao.SOLICITAR_CONSULTA);
        Paciente resposta = enviarSolicitacaoConsulta(dadosPaciente);
        return resposta;
    }

    public DadosServer requisitarDadosServidor() {
        DadosPaciente dadosRecebidos = new DadosPaciente(Requisicao.SOLICITAR_DIAGNOSTICOS);
        DadosServer resposta = enviarSolicitacaoDadosServidor(dadosRecebidos);
        return resposta;
    }

    public void consultar(ArrayList<String> sintomasSelecionados, String nomePaciente) {
        Paciente pacienteConsultado = requisitarConsulta(sintomasSelecionados, nomePaciente);
        this.interfaceUsuario.exibirDiagnostico(pacienteConsultado.getDiagnostico());
    }

    public void listarTodosDiagnosticos() {
        interfaceUsuario.listarDiagnostico(requisitarDadosServidor().getPacientesDiagnosticados());
    }

    public void exibirInterface() {
        interfaceUsuario.setVisible(true);
    }
}
