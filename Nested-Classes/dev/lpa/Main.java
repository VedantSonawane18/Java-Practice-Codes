package dev.lpa;

import dev.lpa.domain.Employee;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void printOrderedList(List<Employee> eList, String sortField){
        int currentYear = LocalDate.now().getYear();

        class MyEmployee{
            Employee containedEmployee;
            int yearsWorked;
            String fullName;

            public MyEmployee(Employee containedEmployee){
                this.containedEmployee = containedEmployee;
                yearsWorked = currentYear - Integer.parseInt(
                        containedEmployee.hireDate().split("/")[2]);
                fullName = String.join(" ", containedEmployee.first(), containedEmployee.last());
            }

            @Override
            public String toString() {
                return "%s has worked for %d years".formatted(fullName, yearsWorked);
            }
        }
        List<MyEmployee> list = new ArrayList<>();
        for(Employee employee : eList){
            list.add(new MyEmployee(employee));
        }

        var comparator = new Comparator<MyEmployee>(){

            @Override
            public int compare(MyEmployee o1, MyEmployee o2) {
                if(sortField.equalsIgnoreCase("name")){
                    return o1.fullName.compareTo(o2.fullName);
                }
                return o1.yearsWorked - o2.yearsWorked;
            }
        };

        list.sort(comparator);
        for(MyEmployee myEmployee : list){
            System.out.println(myEmployee);
        }
    }


    public static void main(String[] args) {
        Employee e1 = new Employee("Vedant", "Sonawane", "18/11/2005");
        Employee e2 = new Employee("Ishita", "Pasalkar", "04/12/2004");
        Employee e3 = new Employee("Ayush", "Gaikwad", "11/10/2003");
        Employee e4 = new Employee("Tim", "Buchalka", "01/01/1979");
        Employee e5 = new Employee("Angela", "Yu", "18/09/1990");

        List<Employee> list = new ArrayList<>(Arrays.asList(e1, e2, e3, e4, e5));

        printOrderedList(list, "year");
    }
}