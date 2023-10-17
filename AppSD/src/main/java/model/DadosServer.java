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
    private ArrayList<String> diagnosticosDisponiveis;
    private ArrayList<Paciente> pacientesDiagnosticados;

    public DadosServer(ArrayList<String> diagnosticosDisponiveis, ArrayList<Paciente> pacientesDiagnosticados) {
        this.diagnosticosDisponiveis = diagnosticosDisponiveis;
        this.pacientesDiagnosticados = pacientesDiagnosticados;
        
    }

    public ArrayList<String> getDiagnosticosDisponiveis() {
        return diagnosticosDisponiveis;
    }

    public ArrayList<Paciente> getPacientesDiagnosticados() {
        return pacientesDiagnosticados;
    }
}
