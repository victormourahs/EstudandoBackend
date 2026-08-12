import java.util.stream.IntStream;

public class Sample03 {
    public static void main(String[] args) {
        //PROGRAMAÇÃO FUNCIONAL - NOVIDADE
        int[] valores = {1,2, 4, 3, 5, 7, 9, 6, 8, 12, 15, 21};
        //iteração interna imprimindo os elementos
        IntStream.of(valores)
                .sorted()
                .forEach(valor -> System.out.printf("%d ", valor));
        System.out.println();
        //maior valor
        int maior = IntStream.of(valores)
                .max().getAsInt();
        int menor = IntStream.of(valores)
                .min().getAsInt();
        int somaComum = IntStream.of(valores)
                        .sum();
        System.out.println("Maior: " + maior + "\n" + "Menor: " + menor);
        System.out.println("Soma comum dos valores: " + somaComum);

        int soma = IntStream.of(valores)
                .reduce(0, (x, y) -> x + y);
        System.out.println("Soma dos valores com reduce: " + soma);

        int somaQuadrada = IntStream.of(valores)
                .reduce(0, (x, y) -> x + y * y);
        System.out.println("Soma dos quadrados: " + somaQuadrada);

        //Ordenar a Lista mostrando apenas os pares da lista
        System.out.println("Valores pares Ordenados");
        IntStream.of(valores)
                .filter(valor -> valor % 2 == 0)
                .sorted()
                .forEach(valor -> System.out.printf("%d ", valor));
    }
}
