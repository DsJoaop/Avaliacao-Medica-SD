/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;


import controller.ControllerPaciente;
import java.util.ArrayList;
import javax.swing.JList;
import model.Paciente;

/**
 *
 * @author joaop
 */
public class Interface extends javax.swing.JFrame {
    private final ControllerPaciente controlador;
    /**
     * Creates new form Interface
     * @param controlador
     */
    public Interface(ControllerPaciente controlador) {
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
        txtNomePaciente = new javax.swing.JTextField();
        btListarPacientes = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDiagnostico = new javax.swing.JTextArea();
        txtTitulo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtTitulo1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtTitulo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtTitulo1.setText("Sistema de diagnóstico");

        txtsintomas1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtsintomas1.setText("Nome Paciente");

        btEnviarDados.setBackground(new java.awt.Color(102, 255, 0));
        btEnviarDados.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btEnviarDados.setText("Enviar");
        btEnviarDados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEnviarDadosActionPerformed(evt);
            }
        });

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Selecionar sintomas : ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N

        listaDeSintomas.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "\n"));
        listaDeSintomas.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        listaDeSintomas.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Tosse", "Coriza", "Febre", "Espirro", "Dor nas articulações", "Inchaço nas articulações", "Fadiga", "Falta de ar", "Dor de cabeça", "Dor no peito" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listaDeSintomas.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        listaDeSintomas.setDoubleBuffered(true);
        listaDeSintomas.setLayoutOrientation(javax.swing.JList.HORIZONTAL_WRAP);
        jScrollPane1.setViewportView(listaDeSintomas);

        jButton2.setBackground(new java.awt.Color(255, 0, 0));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setText("Limpar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btListarPacientes.setBackground(new java.awt.Color(204, 255, 204));
        btListarPacientes.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btListarPacientes.setText("Diagnósticos");
        btListarPacientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btListarPacientesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtsintomas1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNomePaciente))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(27, 27, 27)
                        .addComponent(btEnviarDados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btListarPacientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
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
                    .addComponent(txtNomePaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtsintomas1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(btEnviarDados)
                    .addComponent(btListarPacientes))
                .addGap(19, 19, 19))
        );

        txtDiagnostico.setColumns(20);
        txtDiagnostico.setRows(5);
        jScrollPane2.setViewportView(txtDiagnostico);

        txtTitulo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtTitulo.setText("Diagnóstico");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(txtTitulo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(txtTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    
    private void btEnviarDadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEnviarDadosActionPerformed
    // Obtenha os sintomas selecionados da interface
        ArrayList<String> sintomasSelecionados = adicionarElementosSelecionados(listaDeSintomas);
        String nomePaciente = txtNomePaciente.getText();
        
        if(nomePaciente.isEmpty() || sintomasSelecionados.isEmpty()){
            txtDiagnostico.setText(" Preencha todos os campos!!!");
        }else{
            controlador.consultar(sintomasSelecionados, nomePaciente);
        }
    }//GEN-LAST:event_btEnviarDadosActionPerformed

    private void btListarPacientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btListarPacientesActionPerformed
         controlador.listarTodosDiagnosticos();
    }//GEN-LAST:event_btListarPacientesActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       txtDiagnostico.setText("");
       listaDeSintomas.clearSelection();
       txtNomePaciente.setText("");
    }//GEN-LAST:event_jButton2ActionPerformed

    public void exibirDiagnostico(String diagnosticosServidor) {
        if (!diagnosticosServidor.isEmpty()) {
            txtDiagnostico.setText("Paciente: "+txtNomePaciente.getText()+"\nDiagnóstico: " + diagnosticosServidor);
        }
    }
    
    public void listarDiagnostico(ArrayList<Paciente> pacientes) {
    if (pacientes != null) {
        StringBuilder diagnosticos = new StringBuilder();
        for (Paciente paciente : pacientes) {
            diagnosticos.append(paciente.toString()).append("\n\n");
        }
        txtDiagnostico.setText(diagnosticos.toString());
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
    private javax.swing.JButton btListarPacientes;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> listaDeSintomas;
    private javax.swing.JTextArea txtDiagnostico;
    private javax.swing.JTextField txtNomePaciente;
    private javax.swing.JLabel txtTitulo;
    private javax.swing.JLabel txtTitulo1;
    private javax.swing.JLabel txtsintomas1;
    // End of variables declaration//GEN-END:variables

    
}
