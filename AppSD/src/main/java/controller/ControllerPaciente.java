package controller;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import model.DadosInterface;
import model.Diagnostico;
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

    private <T> T enviarSolicitacao(DadosInterface dados, Class<T> responseType) {
        try (
            Socket socket = conectarAoServidor();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream())
        ) {
            objectOutputStream.writeObject(dados);
            objectOutputStream.flush();
            Object resposta = objectInputStream.readObject();
            return responseType.cast(resposta);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void consultar(ArrayList<String> sintomasSelecionados, String nomePaciente) {
        Paciente paciente = new Paciente(sintomasSelecionados, nomePaciente);
        DadosInterface dadosPaciente = new DadosInterface(paciente, Requisicao.SOLICITAR_CONSULTA);
        Paciente pacienteConsultado = enviarSolicitacao(dadosPaciente, Paciente.class);
        this.interfaceUsuario.exibirDiagnostico(pacienteConsultado.getDiagnostico());
    }

    public String enviarDiagnostico(ArrayList<String> sintomasSelecionados, String diagnostico) {
        Diagnostico dadosDiagnostico = new Diagnostico(diagnostico, sintomasSelecionados);
        DadosInterface dadosInterface = new DadosInterface(dadosDiagnostico, Requisicao.ENVIAR_DIAGNOSTICO);
        return enviarSolicitacao(dadosInterface, String.class);
    }

    public void requisitarDadosServidor() {
        DadosInterface dadosRecebidos = new DadosInterface(Requisicao.SOLICITAR_DIAGNOSTICOS);
        ArrayList pacienteDiagnosticados = enviarSolicitacao(dadosRecebidos, ArrayList.class);
        interfaceUsuario.listarDiagnostico(pacienteDiagnosticados);
    }

    public void exibirInterface() {
        interfaceUsuario.setVisible(true);
    }
}
