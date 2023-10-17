/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author joaop
 */
public class DadosServer implements Serializable{
    private final ArrayList<Paciente> pacientesDiagnosticados;
    private final ArrayList<String> diagnosticadosServer;
    
    public DadosServer(ArrayList<Paciente> pacientesDiagnosticados, ArrayList<String> diagnosticosDisponiveis) {
        this.pacientesDiagnosticados = pacientesDiagnosticados;
        this.diagnosticadosServer = diagnosticosDisponiveis;
    }

    public ArrayList<Paciente> getPacientesDiagnosticados() {
        return pacientesDiagnosticados;
    }

    public ArrayList<String> getDiagnosticadosServer() {
        return diagnosticadosServer;
    }
    
    
    
}
