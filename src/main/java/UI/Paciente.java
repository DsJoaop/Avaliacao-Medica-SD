/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI;

/**
 *
 * @author joaop
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Paciente implements Serializable {
    private List<String> sintomas = new ArrayList<>();
    private List<String> diagnosticos = new ArrayList<>();

    public Paciente(List<String> mensagemRecebida) {
        sintomas.addAll(mensagemRecebida);
    }

    public Paciente() {
    }

    public List<String> getSintomas() {
        return sintomas;
    }

    public void setSintomas(List<String> sintomas) {
        this.sintomas = sintomas;
    }

    public List<String> getDiagnosticos() {
        return diagnosticos;
    }

    public void setDiagnosticos(List<String> diagnosticos) {
        this.diagnosticos = diagnosticos;
    }

    @Override
    public String toString() {
        return "Sintomas: " + sintomas + "\nDiagnosticos: " + diagnosticos;
    }
}
