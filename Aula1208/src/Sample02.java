public class Sample02 {
    static void main(String[] args) {
        int[] valores = {1, 3, 5, 7, 9};
        //Programacao nao funcional, programamos como obter o resultado
        //para somar todoso os elementos
        int soma = 0;
        //percorre o vetor, somando cada elemento
        for (int i = 0; i < valores.length; i++){
            soma += valores[i];
            System.out.println("Soma = " + soma);
        }
    }
}
