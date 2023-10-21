/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import controller.ControllerServer;

/**
 *
 * @author Usuario
 */
public class Main {
    public static void main(String[] args) {
        ControllerServer controlador = new ControllerServer();

        // Inicia o servidor em uma thread separada para não bloquear a execução da interface do usuário
        new Thread(() -> {
            controlador.iniciarServidor();
        }).start();
    }
}
