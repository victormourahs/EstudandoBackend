package controller;

import model.Employee;

import java.util.Arrays;

public class ManagingStaff {

    public ManagingStaff(){

    }

    public static void main(String[] args) {
        Employee[] employees = {
                new Employee("Marcos", "Moraes", 20000, "TI"),
                new Employee("Victor", "Moura", 50000, "TI"),
                new Employee("Pedro", "Andrade", 30000, "MEC"),
                new Employee("Adriana", "Silva", 15000, "MEC")
        };
        var lista = Arrays.asList(employees);
        System.out.println("Lista de todos os empregados");
        lista.forEach(System.out::println);
    }
}
