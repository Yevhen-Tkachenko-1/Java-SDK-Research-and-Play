package version.java8.defaultmethod;

public interface Interface1 extends BaseInterface{

    @Override
    default void test(){
        System.out.println(1);
    }
}
