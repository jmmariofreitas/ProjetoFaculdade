/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetofaculdade;

import entity.Usuario;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import projetofaculdade.conexao.Conexao;
import projetofaculdade.dao.UsuarioDAO;
import utils.Metodos;

/**
 *
 * @author Jmariofreitas
 */
public class ListarUsuarios extends javax.swing.JFrame {
    
     private final Connection conn;
    private final DefaultTableModel model;

    /**
     * Creates new form ListarUsuarios
     */
    public ListarUsuarios() {
       usuarioDAO = new UsuarioDAO();
       metodos = new Metodos();
       
       initComponents();
        conn = Conexao.gConnection();
        model = (DefaultTableModel) tabela.getModel();
        // Limpa as linhas da tabela antes de carregar os dados
        model.setRowCount(0);
        // Define a quantidade de colunas
        model.setColumnCount(5);
        
        String[] titulos = {"ID", "IDADE", "NOME","E-MAIL", "ENDERECO"};
        model.setColumnIdentifiers(titulos);
        
        //carrega do banco na tabela
        carregarUsuarios();
        
       setIcon();
        
    }
    
      private void setIcon() {
       setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/list.png")));
    }
    
     private void carregarUsuarios() {
        String sql = "SELECT id, nome, idade, email, endereco FROM usuarios";

        try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("idade"),
                    rs.getString("email"),
                    rs.getString("endereco")
                    
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar usuários: " + e.getMessage());
        }
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Lista dos Usuarios Cadastrados");

        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "TITULO 5"
            }
        ));
        tabela.setColumnSelectionAllowed(true);
        jScrollPane1.setViewportView(tabela);
        tabela.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        tabela.getAccessibleContext().setAccessibleName("");
        tabela.getAccessibleContext().setAccessibleParent(tabela);

        jButton1.setText("Excluir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Editar Usuario");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Atualizar Tabela");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 974, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:v
        excluirUsuario();
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
       usuarioDAO.metodoEditar();
       
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        atualizarTabela();
    }//GEN-LAST:event_jButton3ActionPerformed

     private void excluirUsuario() {
        String id = JOptionPane.showInputDialog("Informe o ID do usuário a ser excluído:");

        if (id != null && !id.isEmpty()) {
            try {
                int idUsuario = Integer.parseInt(id);
                usuarioDAO.excluirUsuario(idUsuario);
                atualizarTabela();
                  
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "ID de usuário inválido! NEM CHAMOU O userDAO");
            }
        }
    }
     
      // Método para atualizar a tabela após a exclusão de um usuário
   public void atualizarTabela() {
    DefaultTableModel model = (DefaultTableModel) tabela.getModel();
    model.setRowCount(0); // Limpa os dados atuais na tabela
    exibirDadosNaTabela(); // Reexibe os dados atualizados
}
   
    void exibirDadosNaTabela() {
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        List<Usuario> usuarios = usuarioDAO.retornaUsuarios();

        usuarios.stream().forEach((usuario) -> {
            model.addRow(new Object[]{
                usuario.getId(),
                usuario.getNome(),
                usuario.getIdade(),
                usuario.getEmail(),
                usuario.getEndereco()
            });
         });
    }
     
  
    private final Metodos metodos;
    private final UsuarioDAO usuarioDAO;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabela;
    // End of variables declaration//GEN-END:variables
}
