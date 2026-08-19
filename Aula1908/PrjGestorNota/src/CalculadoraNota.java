import java.util.*;
import java.util.stream.Collectors;

public class CalculadoraNota {
    private final List<ItemNota> itens = new ArrayList<>();

    public void adicionarItem(ItemNota item){
        itens.add(item);
    }

    public List<ItemNota> getItens(){
        //Retornar uma visao da lista, ou seja, nao pode ser modificada
        return Collections.unmodifiableList(itens);
    }

    public double calcularTotalGeral(){
        return itens.stream()
                .mapToDouble(ItemNota::calcularSubtotal)
                .sum();
    }

    public List<String> listarProdutosPremium(){
        return itens.stream()
                .filter(item -> item.precoUnitario() > 100)
                .map(ItemNota::produto)
                .sorted()
                .collect(Collectors.toList());

    }

    public Optional<ItemNota> encontrarItemMaisCaro(){
        return itens.stream()
                .max(Comparator.comparingDouble(ItemNota::precoUnitario));
    }
}
