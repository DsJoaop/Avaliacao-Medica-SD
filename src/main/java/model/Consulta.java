package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Consulta implements Serializable {
    private List<String> sintomas = new ArrayList<>();
    private List<String> diagnosticos = new ArrayList<>();

    public Consulta(List<String> mensagemRecebida) {
        sintomas.addAll(mensagemRecebida);
    }

    public Consulta() {
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
