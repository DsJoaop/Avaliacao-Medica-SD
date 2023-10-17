/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;

/**
 *
 * @author joaop
 */
public class DadosPaciente implements Serializable{
    private Paciente paciente;
    private Requisicao requisicao;

    public DadosPaciente(Paciente paciente, Requisicao requisicao) {
        this.paciente = paciente;
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
    
    
}
