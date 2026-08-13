import java.util.Arrays;
import java.util.stream.Collectors;

public class Sample04 {
    public static void main(String[] args) {
        Integer[] values = {23, 11, 10, 101, 2, 90, 26};
        for (int i = 0; i <= 5000000; i++){

        }
        //Imprimir como lista
        System.out.printf("Lista stream: %s%n", Arrays.asList(values));
        //Nova variavel que representa uma copia da lista original ja ordenada
        var listaOrdenada = Arrays.asList(values)
                .stream()
                .sorted()
                .collect(Collectors.toList());
        //Imprimindo a lista ordenada
        System.out.println("Lista Ordenada: ");
        listaOrdenada.forEach(System.out::println);
        //Pares ordenados maiores que 10
        var paresOrdenadosMaioresQ10 = Arrays
                .stream(values)
                .filter(v -> v % 2 == 0)
                .filter(v -> v > 10)
                .sorted()
                .collect(Collectors.toList());
        System.out.printf("Pares ordenados maiores que 10: %s%n", paresOrdenadosMaioresQ10);
        System.out.println();
    }
}
