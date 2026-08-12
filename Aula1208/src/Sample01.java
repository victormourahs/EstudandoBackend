import java.util.ArrayList;
import java.util.List;

public class Sample01 {
    static void main(String[] args) {
        //Lista imutavel de frutas
        var lista = List.of("Banana", "Pera", "Morango", "Abacate");
        //List.of é uma lista imutavel
        for (String fruta : lista){
            System.out.println(fruta);
        }

        //Lista mutavel de frutas
        var frutas = new ArrayList<String>();
        frutas.add("Fruta do conde");
        frutas.add("Rocambole");
        frutas.add("Melao");

        frutas.remove("Rocambole");

        for (String f : frutas){
            System.out.println(f);
        }
    }
}
