import java.util.Scanner;

public class GestorNota {
    private static final Scanner sc = new Scanner(System.in);
    private static final CalculadoraNota calculadora = new CalculadoraNota();
    public static void main(String[] args) {
        var executando = true;
        while (executando){
            exibirMenu();
            try {
            var opcao = Integer.parseInt(sc.nextLine());
                executando = switch (opcao){
                    case 1 -> {execAdicionar(); yield true;}
                    case 2 -> {execCalcularTotal(); yield true;}
                    case 3 -> {execListarPremium(); yield true;}
                    case 4 -> {execEncontrarMaisCaro(); yield true;}
                    case 5 -> {System.out.println("Fim do programa"); yield false;}
                    default -> {System.out.println("Opcao Invalida"); yield true;}
                };
            }catch (NumberFormatException e){
                System.out.println("Valor Invalido. Digite numero");
            }

        }
    }

    private static void exibirMenu(){
        System.out.println("\t\t=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println("\t\tGESTAO DE NOTA FISCAL (SINTAXE MODERNA)");
        System.out.println("\t\t1. Adicionar item na nota");
        System.out.println("\t\t2. Calcular valor total da nota");
        System.out.println("\t\t3. Listar itens premium (preco > 100");
        System.out.println("\t\t4. Visualizar item mais caro");
        System.out.println("\t\t5. Sair");
        System.out.println("\t\tEscolha sua opcao:");
    }

    private static void execAdicionar(){
        try{
            System.out.println("Digite o nome do produto: ");
            var produto = sc.nextLine();
            System.out.println("Digite a quantidade: ");
            var quantidade = Integer.parseInt(sc.nextLine());
            System.out.println("Digite o preco unitario do produto: ");
            var precoUnitario = Double.parseDouble(sc.nextLine());
            calculadora.adicionarItem(new ItemNota(produto, quantidade, precoUnitario));
            System.out.println("Item de nota cadastrado com sucesso");
        }catch (NumberFormatException ex){
            System.out.println("Entrada numerica invalida. Digite novamente");
        }
    }

    private static void execCalcularTotal(){
        var total = calculadora.calcularTotalGeral();
        System.out.println("Total geral da nota: R$ " + total);
    }

    private static void execEncontrarMaisCaro(){
        var itemMaisCaro = calculadora.encontrarItemMaisCaro();
        itemMaisCaro.ifPresentOrElse(
                item -> System.out.printf("Item mais caro %s preco %.2f%n",
                        item.produto(), item.precoUnitario()),
                () -> System.out.println("A nota fical esta vazia.")
        );
    }

    private static void execListarPremium(){
        var premium = calculadora.listarProdutosPremium();
        if (premium.isEmpty()){
            System.out.println("Nenhum item premium encontrado");
        }else {
            premium.forEach(produto -> System.out.println("* " + produto));
        }
    }
}
