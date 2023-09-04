package org.example.contatos;

public class Contato {
    private String nome;
    private String sobrenome;
    private String numero;
    private String email;

    public Contato() {
    }

    public Contato(String nome, String sobrenome, String numero, String email) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.numero = numero;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public String toString() {
        return "Nome: " + nome + " " + sobrenome + ", Telefone: " + numero + ", Email: " + email;
    }
}
