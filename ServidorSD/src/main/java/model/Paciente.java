package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Paciente implements Serializable {
    private String nome;
    private ArrayList<String> sintomas;
    private String diagnosticos;
    
    
    public Paciente(ArrayList<String> pacienteSintomas, String nome) {
        sintomas = pacienteSintomas ;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<String> getSintomas() {
        return sintomas;
    }

    public void setSintomas(ArrayList<String> sintomas) {
        this.sintomas = sintomas;
    }

    public String getDiagnosticos() {
        return diagnosticos;
    }

    public void setDiagnosticos(String diagnostico) {
        this.diagnosticos = diagnostico;
    }

    @Override
    public String toString() {
        return " Nome: " + nome + "\n Sintomas: " + sintomas + "\n Diagnosticos: " + diagnosticos;
    }
}
