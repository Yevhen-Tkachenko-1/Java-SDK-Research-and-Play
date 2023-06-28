package version.java8.defaultmethod;

public interface Interface2 extends BaseInterface{

    @Override
    default void test(){
        System.out.println(2);
    }
}
