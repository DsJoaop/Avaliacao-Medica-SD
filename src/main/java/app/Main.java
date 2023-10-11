/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import controll.Controller;

/**
 *
 * @author Usuario
 */
public class Main {
    public static void main(String[] args) {
        Controller controlador = new Controller();

        // Inicia o servidor em uma thread separada para não bloquear a execução da interface do usuário
        new Thread(() -> {
            controlador.iniciarServidor();
        }).start();

        // Exibe a interface do usuário
        controlador.exiberInterface();
    }
}
