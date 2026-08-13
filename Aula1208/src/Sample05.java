import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Sample05 {
    public static void main(String[] args) {
        var frutas = Arrays.asList("Amora", "Pera", "Abacaxi", "Manga");
        frutas.forEach(System.out::println);

        var frutasEmMaiusculo = frutas
                .stream()
                .map(String::toUpperCase)
                .sorted()
                .collect(Collectors.toList());
        System.out.println(frutasEmMaiusculo);

        var frutasComInicialA = frutasEmMaiusculo
                .stream()
                .filter(fruta -> fruta.startsWith("A"))
                .collect(Collectors.toList());
        System.out.println(frutasComInicialA);
    }
}
