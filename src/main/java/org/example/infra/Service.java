package org.example.infra;

import org.example.contatos.Contato;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Service {
    private static final String ARQUIVO_CONTATOS = "contatos.txt";
    private static List<Contato> contatos = new ArrayList<>();

    public static void ExibirMenu(){
        Scanner scanner = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("\n----- Menu -----");
            System.out.println("1. Listar de contatos");
            System.out.println("2. Adicionar contato");
            System.out.println("3. Pesquisar contato");
            System.out.println("4. Editar contato");
            System.out.println("5. Excluir contato");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    listarContatos();
                    break;
                case 2:
                    adicionarContato();
                    break;
                case 3:
                    pesquisarContato();
                    break;
                case 4:
                    editarContato();
                    break;
                case 5:
                    excluirContato();
                    break;
                case 6:
                    salvarContatos();
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 6);
    }

    private static void listarContatos() {
        if (contatos.isEmpty()){
            System.out.println("A lista de contato está vazia!");
        }else {
            System.out.println("\"\\n----- Contatos -----\"");
            for (Contato contato : contatos) {
                System.out.println(contato);
            }
        }
    }

    private static void adicionarContato() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Sobrenome: ");
        String sobrenome = scanner.nextLine();
        System.out.print("Numero: ");
        String numero = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();

        contatos.add(new Contato(nome, sobrenome, numero, email));
        System.out.println("Contato adicionado com sucesso!");
    }

    private static void pesquisarContato() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Informe o nome: ");
        String nome = scanner.next();

        boolean contem = false;

        for (Contato contato : contatos) {
            if (contato.getNome().toLowerCase().contains(nome.toLowerCase())){
                System.out.println(contato);
                contem = true;
            }
        }
        if (!contem) {
            System.out.println("Nenhum contato encontrado com esse nome.");
        }

    }

    private static void editarContato() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Informe o nome que deseja editar: ");
        String nomePraEditar = scanner.next();

        boolean contem = false;
        for (Contato contato : contatos) {
            if (contato.getNome().equalsIgnoreCase(nomePraEditar)){
                System.out.print("Novo nome: ");
                scanner.nextLine();
                String novoNome = scanner.nextLine();
                System.out.print("Novo Sobrenome: ");
                String novoSobrenome = scanner.nextLine();
                System.out.print("Novo telefone: ");
                String novoTelefone = scanner.nextLine();
                System.out.print("Novo email: ");
                String novoEmail = scanner.nextLine();

                contato.setNome(novoNome);
                contato.setSobrenome(novoSobrenome);
                contato.setNumero(novoTelefone);
                contato.setEmail(novoEmail);

                System.out.println("Contato editado com sucesso!");
                contem = true;
                break;
            }
        }
        if (!contem){
            System.out.println("Contato não encontrado!");
        }


    }

    private static void excluirContato() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o nome do contato a ser excluído: ");
        String nomeExcluir = scanner.nextLine();

        boolean excluir = contatos.removeIf(contato -> contato.getNome().equalsIgnoreCase(nomeExcluir));

        if (excluir){
            System.out.println("Contato excluído com sucesso!");
        } else {
            System.out.println("Contato não encontrado.");
        }
    }

    private static void salvarContatos() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO_CONTATOS))){
            for (Contato contato : contatos) {
                bw.write(contato.getNome() + " " + contato.getSobrenome() + ", " + contato.getNumero() + ", " + contato.getEmail());
                bw.newLine();
            }
            System.out.println("Contato salvo com sucesso!!!");
        }catch (IOException e){
            System.err.println("Erro ao salvar contatos: " + e.getMessage());
        }
    }
    private static void carregarContatos(){
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_CONTATOS))) {
            String linha;
            while ((linha = br.readLine()) != null){
                String[] partes = linha.split(",");
                if (partes.length == 4 ){
                    contatos.add(new Contato(partes[0], partes[1], partes[2], partes[3]));
                }
            }
            System.out.println("Contatos carregados com sucesso.");
        }catch (IOException e){
            System.err.println("Erro ao carregar contatos: " + e.getMessage());
        }
    }

}
