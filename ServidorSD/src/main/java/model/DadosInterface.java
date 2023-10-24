/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import controller.Requisicao;
import java.io.Serializable;

/**
 *
 * @author joaop
 */
public class DadosInterface implements Serializable{
    private Paciente paciente;
    private Diagnostico diagnostico;
    private Requisicao requisicao;
    
    
    public DadosInterface(Requisicao requisicao) {
        this.requisicao = requisicao;
    }
    

    public DadosInterface(Paciente paciente, Requisicao requisicao) {
        this.paciente = paciente;
        this.requisicao = requisicao;
    }
    
    public DadosInterface(Diagnostico diagnostico, Requisicao requisicao) {
        this.diagnostico = diagnostico;
        this.requisicao = requisicao;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Requisicao getRequisicao() {
        return requisicao;
    }

    public void setRequisicao(Requisicao requisicao) {
        this.requisicao = requisicao;
    }

    public Diagnostico getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(Diagnostico diagnostico) {
        this.diagnostico = diagnostico;
    }
    
    
    
}
