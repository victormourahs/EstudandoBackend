package model;

public record Paciente(String nome, int idade, NivelEmergencia nivel, boolean possuiPlano) {
    public Paciente {
        if (idade < 0){
            throw new IllegalArgumentException("Idade nao pode ser menor que 0. Tente Novamente.");
        }

        if (nome == null || nome.isBlank()){
            throw new IllegalArgumentException("Nome nao pode ser vazio ou nulo. Tente Novamente.");
        }
    }
}
