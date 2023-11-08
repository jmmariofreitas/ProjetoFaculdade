
package entity;

/**
 *
 * @author Jmariofreitas
 */

public class Usuario {

    public String getNome() {
        return nome;
    }

    public String getId() {
        return id;
    }

    public String getIdade() {
        return idade;
    }

    public String getEmail() {
        return email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

 
    private String nome;
    private String id;
    private String idade;
    private String email;
    private String endereco;

    


}
