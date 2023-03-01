package java8.defaultmethod;

public interface BaseInterface {

    default void test(){
        System.out.println("base");
    }
}
