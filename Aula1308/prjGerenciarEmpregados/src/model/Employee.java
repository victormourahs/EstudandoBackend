package model;

public class Employee {
    private String firstName;
    private String lastName;
    private double salary;
    private String departament;

    public Employee(String firstName, String lastName, double salary, String departament) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.departament = departament;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDepartament() {
        return departament;
    }

    public void setDepartament(String departament) {
        this.departament = departament;
    }

    public String getName(){
        return lastName + ", " + firstName;
    }

    @Override
    public String toString() {
        return String.format(
                "%-8s %-8s %8.2f %s",
                firstName, lastName, salary, departament
        );
    }
}
