package view;

import model.Frota;
import model.Nave;
import model.StatusNave;

import java.util.List;
import java.util.Scanner;

public class GestorEstelar {
    Frota frota = new Frota();
    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        GestorEstelar gestorEstelar = new GestorEstelar();
        int opcao = 0;

        do {
            System.out.println("Bem vindo a Gestao Estelar!");
            System.out.println(">>>MENU<<<");
            System.out.println("1 - Cadastrar Nave");
            System.out.println("2 - Listar Frota");
            System.out.println("3 - Consultar Por Registro");
            System.out.println("4 - Atualizar Status");
            System.out.println("5 - Remover da Frota");
            System.out.println("6 - Relatorio de Prontidao");
            System.out.println("7 - Sair");
            System.out.println("Escolha uma opcao para continuar: ");
            try {
                opcao = Integer.parseInt(gestorEstelar.sc.nextLine());
            }catch (NumberFormatException e){
                System.out.println("Erro: " + e.getMessage());
            }
            switch (opcao){
                case 1 -> gestorEstelar.execCadastrar();
                case 2 -> gestorEstelar.execListar(gestorEstelar.frota.getNaves());
                case 3 -> gestorEstelar.execConsultar();
                case 4 -> gestorEstelar.execAtualizarStatus();
                case 5 -> gestorEstelar.execExcluir();
                case 6 -> gestorEstelar.execRelatorioDeProntidao();
                case 7 -> System.out.println("FIM!");
                default -> System.out.println("Opcao Invalida.");
            }
        }while (opcao != 7);
    }

    public void execCadastrar(){
        String registro;
        String modelo;
        int autonomiaAnosLuz = 0;

        System.out.println("Cadastro de Naves: ");
        System.out.println("Informe o Número de Registro da Nave: ");
        registro = sc.nextLine();
        System.out.println("Informe o Modelo da Nave: ");
        modelo = sc.nextLine();
        System.out.println("Informe a Autonomia da Nave: ");
        try {
            autonomiaAnosLuz = Integer.parseInt(sc.nextLine());
        }catch (NumberFormatException e){
            e.getStackTrace();
        }
        System.out.println("Informe o Status da Nave: ");
        System.out.println("1 - Nave Operacional");
        System.out.println("2 - Nave em Reparo");
        System.out.println("3 - Nave desativada");
        System.out.println("4 - Nave em missao");
        System.out.println("5 - Nave em teste de voo");
        int opcao = Integer.parseInt(sc.nextLine());
        StatusNave status = switch(opcao){
            case 2 -> StatusNave.EM_REPARO;
            case 3 -> StatusNave.DESATIVADA;
            case 4 -> StatusNave.EM_MISSAO;
            case 5 -> StatusNave.TESTE_DE_VOO;
            default -> StatusNave.OPERACIONAL;
        };
        Nave nave = new Nave(registro, modelo, autonomiaAnosLuz, status);
        frota.adicionar(nave);
        System.out.println("Nave Adicionada.");
    }

    public void execListar(List<Nave> naves){
        if (!naves.isEmpty()) {
            for (Nave nave : naves) {
                System.out.println("Dados da Nave: ");
                System.out.println("Registro: " + nave.registro());
                System.out.println("Modelo: " + nave.modelo());
                System.out.println("Autonomia Anos Luz: " + + nave.autonomiaAnosLuz());
                System.out.println(nave.obterStatusSimplificado());
                System.out.println("------------------------------");
            }
        }else {
            System.out.println("Nenhuma nave cadastrada ainda! Para cadastrar digite 1.");
        }
    }

    public void execConsultar(){
        System.out.println("Informe o registro que voce deseja buscar: ");
        String registro = sc.nextLine();
        try {
        Nave nave = frota.buscarPorRegistro(registro)
                .orElseThrow(() -> new IllegalArgumentException("Nave desconhecida"));
        System.out.println("Dados da Nave: ");
        System.out.println("Registro: " + nave.registro());
        System.out.println("Modelo: " + nave.modelo());
        System.out.println("Autonomia Anos Luz: " + + nave.autonomiaAnosLuz());
        System.out.println(nave.obterStatusSimplificado());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public void execAtualizarStatus(){
        System.out.println("Informe o registro da nave que deseja alterar o status: ");
        String registro = sc.nextLine();
        try {
        var nave = frota.buscarPorRegistro(registro)
                .orElseThrow(() -> new IllegalArgumentException("Nave desconhecida"));
        System.out.println("Informe o novo status da nave: ");
            System.out.println("1 - Nave Operacional");
            System.out.println("2 - Nave em Reparo");
            System.out.println("3 - Nave desativada");
            System.out.println("4 - Nave em missao");
            System.out.println("5 - Nave em teste de voo");
            int opcao = 0;
            try {
                opcao = Integer.parseInt(sc.nextLine());
            }catch (NumberFormatException e){
                System.out.println("Erro: " + e.getMessage());
            }
            StatusNave statusAtualizado = switch(opcao){
                case 2 -> StatusNave.EM_REPARO;
                case 3 -> StatusNave.DESATIVADA;
                case 4 -> StatusNave.EM_MISSAO;
                case 5 -> StatusNave.TESTE_DE_VOO;
                default -> StatusNave.OPERACIONAL;
            };
        Nave naveAtualizada = new Nave(nave.registro(), nave.modelo(), nave.autonomiaAnosLuz(), statusAtualizado);
        frota.getNaves().remove(nave);
        frota.adicionar(naveAtualizada);
        System.out.println("Status da nave atualizado com sucesso!");
        }catch (IllegalArgumentException e){
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void execExcluir(){
        System.out.println("Informe o registro que voce deseja remover: ");
        String registro = sc.nextLine();
        if (frota.remover(registro)){
            System.out.println("Nave removida com sucesso!");
        }else {
            System.out.println("Nave nao encontrada");
        }
    }

    public void execRelatorioDeProntidao(){
        if (!frota.getNaves().isEmpty()) {
            int navesProntas = 0;
            int navesEmReparo = 0;
            for (Nave nave : frota.getNaves()) {
                if (nave.status().equals(StatusNave.OPERACIONAL)) {
                    navesProntas++;
                } else if (nave.status().equals(StatusNave.EM_REPARO)) {
                    navesEmReparo++;
                }
            }
            System.out.println("Total de Naves Prontas: " + navesProntas);
            System.out.println("Total de Naves em Reparo: " + navesEmReparo);
        }else {
            System.out.println("Nenhuma nave cadastrada.");
        }

    }
}
