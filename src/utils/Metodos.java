package utils;

import entity.Usuario;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import projetofaculdade.EditarUsuario;
import projetofaculdade.ListarUsuarios;
import projetofaculdade.dao.UsuarioDAO;


/**
 *
 * @author Jmariofreitas
 */
public class Metodos {
    
    
    public Metodos() {
       usuarioDAO = new UsuarioDAO();
    }
    
    public void metodoEditar(){

          String idUsuario = JOptionPane.showInputDialog("Digite o ID do usuário a ser editado:");
         if (idUsuario != null && !idUsuario.isEmpty()) {
             
              int id = Integer.parseInt(idUsuario);
              Usuario usuario;
              usuario = usuarioDAO.buscarUsuario(id);
              if(usuario != null){
                  
                 
                  EditarUsuario editarUsuarioForm;
                  editarUsuarioForm = new EditarUsuario();
                  editarUsuarioForm.preencherCampos(usuario);
                  editarUsuarioForm.setVisible(true); 
                  
              }else{
                  System.out.println("Nenhum usuario encontrado para esse ID");
              
              }
         
         }
         
         final ListarUsuarios atualizarTabela = new ListarUsuarios();
        
         
    }
    
   public void metodoAlteraIcone(String caminho){
    
       setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(caminho)));
    
    }
    
   
    
 private final UsuarioDAO usuarioDAO;

    private void setIconImage(Image image) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
