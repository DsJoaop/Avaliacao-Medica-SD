/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;


import controll.Controller;
import java.util.ArrayList;
import javax.swing.JList;

/**
 *
 * @author joaop
 */
public class Interface extends javax.swing.JFrame {
    private final Controller controlador;
    /**
     * Creates new form Interface
     * @param controlador
     */
    public Interface(Controller controlador) {
        initComponents();
        this.controlador = controlador;
    }
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtTitulo1 = new javax.swing.JLabel();
        txtsintomas1 = new javax.swing.JLabel();
        btEnviarDados = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaDeSintomas = new javax.swing.JList<>();
        jButton2 = new javax.swing.JButton();
        txtNomeMedico = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDiagnostico = new javax.swing.JTextArea();
        txtsintomas2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtTitulo1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtTitulo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtTitulo1.setText("Sistema de diagnóstico");

        txtsintomas1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtsintomas1.setText("Nome médico");

        btEnviarDados.setBackground(new java.awt.Color(102, 255, 0));
        btEnviarDados.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btEnviarDados.setText("Enviar");
        btEnviarDados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEnviarDadosActionPerformed(evt);
            }
        });

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Selecione um ou mais sintomas"));

        listaDeSintomas.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "\n"));
        listaDeSintomas.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        listaDeSintomas.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Febre alta repentina", "Tosse persistente", "Dor de cabeça intensa", "Falta de ar", "Fadiga extrema", "Dor abdominal intensa", "Sangramento anormal", "Perda de peso inexplicável", "Visão turva ou embaçada", "Vômitos frequentes" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listaDeSintomas.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        listaDeSintomas.setDoubleBuffered(true);
        listaDeSintomas.setLayoutOrientation(javax.swing.JList.HORIZONTAL_WRAP);
        jScrollPane1.setViewportView(listaDeSintomas);

        jButton2.setBackground(new java.awt.Color(255, 0, 0));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setText("Cancelar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtsintomas1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNomeMedico))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(36, 36, 36)
                        .addComponent(btEnviarDados, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(txtTitulo1)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(txtTitulo1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNomeMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtsintomas1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btEnviarDados)
                    .addComponent(jButton2)))
        );

        txtDiagnostico.setColumns(20);
        txtDiagnostico.setRows(5);
        jScrollPane2.setViewportView(txtDiagnostico);

        txtsintomas2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtsintomas2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtsintomas2.setText("Diagnóstico");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtsintomas2)
                .addGap(77, 77, 77))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(txtsintomas2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    
    private void btEnviarDadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEnviarDadosActionPerformed
    // Obtenha os sintomas selecionados da interface
        ArrayList<String> sintomasSelecionados = adicionarElementosSelecionados(listaDeSintomas);
        String nomeMedico = txtNomeMedico.getText();
        controlador.processarConsulta(sintomasSelecionados, nomeMedico);
    }//GEN-LAST:event_btEnviarDadosActionPerformed

    public void exibirDiagnostico(ArrayList<String> dadosServidor) {
        if (dadosServidor != null) {
            for (String str : dadosServidor) {
                System.out.println("Os dados recebidos do servidor foram: " + str);
                txtDiagnostico.setText(str);
            }
        }
    }
    
    public ArrayList<String> adicionarElementosSelecionados(JList<String> jList) {
        ArrayList<String> elementosSelecionados = new ArrayList<>();
        int[] indicesSelecionados = jList.getSelectedIndices();

        for (int indice : indicesSelecionados) {
            elementosSelecionados.add(jList.getModel().getElementAt(indice));
        }

        return elementosSelecionados;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btEnviarDados;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> listaDeSintomas;
    private javax.swing.JTextArea txtDiagnostico;
    private javax.swing.JTextField txtNomeMedico;
    private javax.swing.JLabel txtTitulo1;
    private javax.swing.JLabel txtsintomas1;
    private javax.swing.JLabel txtsintomas2;
    // End of variables declaration//GEN-END:variables
}