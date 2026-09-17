package view;

import model.Hospital;
import model.NivelEmergencia;
import model.Paciente;

import java.util.Optional;
import java.util.OptionalDouble;
import java.util.Scanner;

public class GestorHospitalar {
    public static final Scanner sc = new Scanner(System.in);
    public static final Hospital gestor = new Hospital();

    public static void main(String[] args) {
        var executando = true;
        while(executando){
            exibirMenu();
            try {
                var opcao = Integer.parseInt(sc.nextLine());
            executando = switch (opcao){
                case 1 -> {
                    execAdmitir();
                    yield true;
                }
                case 2 -> {
                    execRelatorio();
                    yield true;
                }
                case 3 -> {
                    exibirPainel();
                    yield true;
                }
                case 4 -> {
                    execBuscarCasoDeRisco();
                    yield true;
                }
                case 5 -> {
                    System.out.println("Fim do programa.");
                    yield  false;
                }
                default -> {
                    System.out.println("Opcao Invalida");
                    yield true;
                }
            };
            }
            catch (NumberFormatException e){
                System.out.println("Caractere errado. Digite apenas numeros.");
            }
        }
    }

    public static void exibirMenu(){
        System.out.println("\t\t=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println("\t\tGESTAO HOSPITALAR");
        System.out.println("\t\t1. Admitir Paciente");
        System.out.println("\t\t2. Relatorio De Triagem");
        System.out.println("\t\t3. Painel de Estatisticas");
        System.out.println("\t\t4. Buscar Caso de Risco");
        System.out.println("\t\t5. Sair");
        System.out.println("\t\tEscolha uma opcao: ");
    }

    public static void execAdmitir(){
        try {
            System.out.println("Informe o nome do paciente: ");
            var nome = sc.nextLine();

            System.out.println("Informe a idade do paciente");
            var idade = Integer.parseInt(sc.nextLine());

            System.out.println("Informe o Nivel de Emergencia: ");
            System.out.println("1 - Leve");
            System.out.println("2 - Moderado");
            System.out.println("3 - Urgente");
            System.out.println("4 - Critico");
            NivelEmergencia nivel = switch (Integer.parseInt(sc.nextLine())) {
                case 2 -> NivelEmergencia.MODERADO;
                case 3 -> NivelEmergencia.URGENTE;
                case 4 -> NivelEmergencia.CRITICO;
                default -> NivelEmergencia.LEVE;
            };

            System.out.println("O paciente possui plano?");
            System.out.println("1 - Sim");
            System.out.println("2 - Nao");
            var possuiPlano = Integer.parseInt(sc.nextLine());
            boolean plano = possuiPlano == 1;

            gestor.admitir(new Paciente(nome, idade, nivel, plano));
            System.out.println("Item cadastrado com sucesso");
        }catch (NumberFormatException e){
            System.out.println("Erro: entrada invalida");
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    public static void execRelatorio(){
        var relatorio = gestor.listarEmergencias();
        if (relatorio.isEmpty()){
            System.out.println("Nenhum paciente em caso urgente ou critico");
        }else {
            System.out.println("Paciente(s) em emergencia/estado critico: ");
            relatorio.forEach(paciente -> System.out.println(" * " + paciente));
        }
    }

    public static void exibirPainel(){
        OptionalDouble mediaIdade = gestor.calcularMediaIdadeCriticos();
        long qtdSegurados = gestor.contarSegurados();

        mediaIdade.ifPresentOrElse(
                idade -> System.out.println("A media de idade em estado critico eh de: " + idade),
                () -> System.out.println("Nenhum paciente em estado critico")
        );

        if (qtdSegurados <= 0){
            System.out.println("Nenhum paciente segurado");
        }else {
            System.out.printf("O hospital possui %d pacientes segurados \n", qtdSegurados);
        }
    }

    public static void execBuscarCasoDeRisco(){
        Optional<Paciente> paciente = gestor.buscarPacienteMaisIdoso();
        paciente.ifPresentOrElse(
                idoso -> System.out.println("O paciente com a maior idade eh: " + idoso),
                () -> System.out.println("Nenhum paciente encontrado.")
        );
    }
}
