package model;

import java.util.*;
import java.util.stream.Collectors;

public class Hospital {
    List<Paciente> pacientes = new ArrayList<>();

    public void admitir(Paciente paciente){
        pacientes.add(paciente);
    }

    public List<Paciente> listarEmergencias(){
        return pacientes.stream()
                .filter(paciente -> paciente.nivel().equals(NivelEmergencia.CRITICO)
                || paciente.nivel().equals(NivelEmergencia.URGENTE))
                .sorted(Comparator.comparing(Paciente::idade).reversed())
                .collect(Collectors.toList());
    }

    public OptionalDouble calcularMediaIdadeCriticos(){
        return pacientes.stream()
                .filter(paciente -> paciente.nivel().equals(NivelEmergencia.CRITICO))
                .mapToDouble(Paciente::idade)
                .average();
    }

    public long contarSegurados(){
        return pacientes.stream()
                .filter(Paciente::possuiPlano)
                .count();
    }

    public Optional<Paciente> buscarPacienteMaisIdoso(){
        return pacientes.stream()
                .max(Comparator.comparingInt(Paciente::idade));
    }
}
