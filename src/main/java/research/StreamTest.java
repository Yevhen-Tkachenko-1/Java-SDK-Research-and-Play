package research;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class StreamTest {

    public static void main(String[] args) {

        filter();
    }

    static void filter(){
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Yevhen", 10000));
        employees.add(new Employee("John", 9000));
        employees.add(null);
        employees.add(new Employee("David", 8000));
        employees.add(new Employee("Emily", 7000));
        employees.add(null);
        employees.add(new Employee("Danielle", 6000));
        employees.add(new Employee("Daniel", 5000));
        employees.add(null);
        employees.add(new Employee("Dominic", 4000));
        employees.add(new Employee("Diana", 3000));
        List<Employee> resultList = employees.stream()
                        .filter(Objects::nonNull)
                        .filter(employee -> employee.name.startsWith("D") && employee.salary>4000)
                        .collect(Collectors.toList());
        Optional<List<Employee>> result = Optional.ofNullable(resultList.isEmpty()?null:resultList);
        System.out.println(result);
    }

    static void joining(){
        String value = new ArrayList<String>().stream().collect(Collectors.joining());

        System.out.println(value.isEmpty());
        System.out.println("["+value+"]");

    }

    record Employee(String name, Integer salary){}

}
