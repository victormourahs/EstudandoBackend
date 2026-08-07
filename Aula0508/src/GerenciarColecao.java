import java.util.Scanner;

public class GerenciarColecao {
    Prateleira prateleira = new Prateleira();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GerenciarColecao gerenciarColecao = new GerenciarColecao();
        int opcao = 0;
        do {
            System.out.println(">>> MENU <<<");
            System.out.println("1. Cadastrar Vinil");
            System.out.println("2. Procurar Vinil");
            System.out.println("3. Mostrar a lista de vinis");
            System.out.println("9. Sair");
            System.out.println("Digite sua opcao: ");
            try {
            opcao = Integer.parseInt(sc.nextLine());
            }catch (NumberFormatException e){
                e.getStackTrace();
            }
            switch (opcao){
                case 1 -> gerenciarColecao.execCadastrar();
                case 2 -> gerenciarColecao.execConcultar();
                case 3 -> gerenciarColecao.execListar();
                case 9 -> System.out.println("FIM!");
                default -> System.out.println("Opcao Invalida");
            }
        }while(opcao != 9);
    }

    public void execCadastrar(){
        //cadastrar um novo vinil a colecao
        Scanner sc = new Scanner(System.in);
        String titulo, artista;
        int ano;
        EstadoDeConservacao estado;
        System.out.println("Digite o titulo do vinil a ser cadastrado: ");
        titulo = sc.nextLine();
        System.out.println("Digite o nome do artista: ");
        artista = sc.nextLine();
        System.out.println("Digite o ano do vinil: ");
        ano = Integer.parseInt(sc.nextLine());
        System.out.println("Digite o estado de conservacao: ");
        System.out.println("1 - Novo");
        System.out.println("2 - Excelente");
        System.out.println("3 - Usado");
        System.out.println("4- Raro");
        System.out.println("5 - Danificado");
        int opcao = Integer.parseInt(sc.nextLine());
        estado = switch (opcao){
            case 2 -> EstadoDeConservacao.EXCELENTE;
            case 3 -> EstadoDeConservacao.USADO;
            case 4 -> EstadoDeConservacao.RARO;
            case 5 -> EstadoDeConservacao.DANIFICADO;
            default -> EstadoDeConservacao.NOVO;
        };

        var vinil = new Vinil(titulo, artista, ano, estado);
        prateleira.adicionar(vinil);
    }

    public void execConcultar() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o titulo a ser encontrado: ");
        String titulo = sc.nextLine();
        Vinil vinil = null;
        vinil = prateleira.buscarPorTitulo(titulo);
        if (vinil != null){
            System.out.println("Vinil Encontrado");
            System.out.println("Disco Titulo: " + vinil.titulo());
            System.out.println("Artista: " + vinil.artista());
            System.out.println("Ano de Producao: " + vinil.ano());
            System.out.println("Estado de Conservacao: " + vinil.estado());
        }else{
            System.out.println("Titulo Nao Encontrado.");
        }
    }

    public void execListar(){
        var lista = prateleira.listarTodos();
        for (Vinil v : lista){
            System.out.println("Disco Titulo: " + v.titulo());
            System.out.println("Artista: " + v.artista());
            System.out.println("Ano de Producao: " + v.ano());
            System.out.println("Estado de Conservacao: " + v.estado());
        }
    }
}
