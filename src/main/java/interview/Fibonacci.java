package interview;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Fibonacci {

    public static void main(String[] args) throws Exception {
        var s = "Hello";
        System.out.println(fibonacci(3));
        System.out.println(fibonacci(5));
        System.out.println(fibonacci(7));
        //System.out.println(fibonacci(-12));
        System.out.println(fibonacci(12000));
        System.out.println(fibonacci(12000));
    }

    // 0 1 1 2 3 5 8 13
    // 3 -> 2
    static BigInteger fibonacci(int index) throws Exception{

        if(index < 0){
            throw new Exception("Index can't be neganive: "+index);
        }

        if(saved.containsKey(index)){
            System.out.println("Return saved value");
            return saved.get(index);
        }

        BigInteger previous = BigInteger.ZERO;
        BigInteger res = BigInteger.ONE;

        if(index == 0){
            return BigInteger.ZERO;
        }
        for (int i = 1; i < index; i++) {
            BigInteger temp = res;
            res = res.add(previous);
            previous = temp;
        }
        saved.put(index, res);
        return res;
    }

    static Map<Integer, BigInteger> saved = new HashMap<>();


    // https://company.com/getFibonacci/{1}-{uid}
    // https://company.com//getFibonacci/{1}-{uid}
}
