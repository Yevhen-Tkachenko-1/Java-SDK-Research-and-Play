package version.java10;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class LocalVariableVar {

    public static void main(String[] args) {

        // ============================= right using =========================================

        var message = "Hello, Java 10";
        System.out.println(message instanceof String);

        var list = new ArrayList<String>();
        var idToNameMap = new HashMap<Integer, String>();

        var arr1 = new int[]{1, 2, 3};

        // ============================= invalid ==================================

        // compilation error
        //var n; // cannot infer type for local variable n: cannot use 'var' on variable without initializer

        // compilation error
        //var empty = null; //cannot infer type for local variable empty: variable initializer is 'null'

        //var arr2 = {1, 2, 3}; // error: array initializer needs an explicit target-type

        //var p = (String s) -> s.length() > 10; // error: lambda expression needs an explicit target-type

        // unexpected error
        var obj = new Object() {}; // crates <anonymous java.lang.Object> and not java.lang.Object
        //obj = new Object(); // incompatible types: java.lang.Object cannot be converted to <anonymous java.lang.Object>

        // unexpected result
        var empList = new ArrayList<>(); // empList will is ArrayList<Object>
        empList.add("test"); // and not ArrayList<String>
        //empList.get(0).length(); // Cannot resolve method 'length' in 'Object'

        // ============================= not a good idea ===========================

        // code become less readable
        var result = new Processor().process();

        // code become less readable
        var x = List.of(1,2,3,4).stream()
                .filter(integer -> integer >2)
                .map(Integer::doubleValue)
                .max(Comparator.naturalOrder())
                .map(value -> String.valueOf(value))
                .orElse("0.0");

    }
    //public var = "hello"; // error: 'var' is not allowed here

    static class Processor{

        Integer process(){
            return 1;
        }
    }
}
