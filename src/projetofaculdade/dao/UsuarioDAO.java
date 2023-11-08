
package projetofaculdade.dao;

import entity.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import projetofaculdade.EditarUsuario;
import projetofaculdade.conexao.Conexao;

/**
 *
 * @author Jmariofreitas
 */



public class UsuarioDAO {

    public void cadastrarUsuario(Usuario usuario){
        String sql = "INSERT INTO USUARIOS (nome, idade, email, endereco) VALUES (?,?,?,?)";

        try (Connection connection = Conexao.gConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getIdade());
            ps.setString(3, usuario.getEmail());
            ps.setString(4, usuario.getEndereco());
          

            ps.execute();
        } catch (SQLException em) {
            System.err.printf(" "+em);
        }
    }

    public void exibirUsuarios() {
        String sql = "SELECT nome, idade, email, endereco FROM USUARIOS";

        try (Connection connection = Conexao.gConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Usuario usuario = new Usuario();
                usuario.setNome(resultSet.getString("nome"));
                usuario.setIdade(resultSet.getString("login"));
                usuario.setEmail(resultSet.getString("senha"));
                usuario.setEndereco(resultSet.getString("email"));
                

                System.out.println("Nome: " + usuario.getNome());
                System.out.println("Idade: " + usuario.getIdade());
                System.out.println("Email: " + usuario.getEmail());
                System.out.println("Endereco: " + usuario.getEndereco());
                
                System.out.println("----------------------");
            }
        } catch (SQLException e) {
        }
    }

    public List<Usuario> retornaUsuarios() {
    List<Usuario> listaUsuarios = new ArrayList<>();

    String sql = "SELECT id, nome, idade, email, endereco FROM USUARIOS";

    try (Connection connection = Conexao.gConnection();
         PreparedStatement statement = connection.prepareStatement(sql);
         ResultSet resultSet = statement.executeQuery()) {

        while (resultSet.next()) {
            Usuario usuario = new Usuario();
            usuario.setId(resultSet.getString("id"));
            usuario.setNome(resultSet.getString("nome"));
            usuario.setIdade(resultSet.getString("Idade"));
            usuario.setEmail(resultSet.getString("email"));
            usuario.setEndereco(resultSet.getString("endereco"));
          

            listaUsuarios.add(usuario);
        }
    } catch (SQLException e) {
    }

    return listaUsuarios;
 }

 public void excluirUsuario(int idUsuario) {
    String sql = "DELETE FROM USUARIOS WHERE id = ?";

    try (Connection connection = Conexao.gConnection();
         PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setInt(1, idUsuario);
        int rowsAffected = ps.executeUpdate();
        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(null, "Usuário excluído com sucesso!");
            
        } else {
            JOptionPane.showMessageDialog(null, "Nenhum usuário foi excluído.");
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao excluir usuário. CHEQUE SE A TABELA É CORRETA, TENTOU NA"+extractTableName(sql));
    }
}
 
 public static String extractTableName(String sql) {
    String upperCaseSQL = sql.toUpperCase();
    int fromIndex = upperCaseSQL.indexOf("FROM") + 4; // Encontra o índice da palavra "FROM"
    int spaceAfterFromIndex = upperCaseSQL.indexOf(" ", fromIndex); // Encontra o próximo espaço após "FROM"
    String tableName = sql.substring(fromIndex, spaceAfterFromIndex).trim(); // Extrai o nome da tabela
    return tableName;
}
public Usuario buscarUsuario(int id) {
    Usuario usuario = null;
    String sql = "SELECT * FROM USUARIOS WHERE id = ?";

    try (Connection connection = Conexao.gConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {

        statement.setInt(1, id); // Identificar o parâmetro na consulta SQL
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            usuario = new Usuario();
            usuario.setId(resultSet.getString("id"));
            usuario.setNome(resultSet.getString("nome"));
            usuario.setIdade(resultSet.getString("idade"));
            usuario.setEmail(resultSet.getString("email"));
            usuario.setEndereco(resultSet.getString("endereco"));
           
        }
    } catch (SQLException e) {
    }
    return usuario;
}


public boolean atualizarUsuario(Usuario usuario) {
    String sql = "UPDATE USUARIOS SET nome = ?, idade = ?, email = ?, endereco = ? WHERE id = ?";

    try (Connection connection = Conexao.gConnection();
         PreparedStatement ps = connection.prepareStatement(sql)) {

        ps.setString(1, usuario.getNome());
        ps.setString(2, usuario.getIdade());
        ps.setString(3, usuario.getEmail());
        ps.setString(4, usuario.getEndereco());
        ps.setString(5, usuario.getId());

        int rowsUpdated = ps.executeUpdate();

        return rowsUpdated > 0; // Retorna verdadeiro se a atualização foi bem-sucedida
    } catch (SQLException e) {
    }
    return false; // Retorna falso em caso de falha na atualização
}


public void metodoEditar(){

          String idUsuario = JOptionPane.showInputDialog("Digite o ID do usuário a ser editado:");
         if (idUsuario != null && !idUsuario.isEmpty()) {
             
              int id = Integer.parseInt(idUsuario);
              Usuario usuario;
              usuario = buscarUsuario(id);
              if(usuario != null){
                  
                 
                  EditarUsuario editarUsuarioForm = new EditarUsuario();
                  editarUsuarioForm.preencherCampos(usuario);
                  editarUsuarioForm.setVisible(true); 
                  
              }else{
                  System.out.println("Nenhum usuario encontrado para esse ID");
              
              }
         
         }
}




}
