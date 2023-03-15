package java15.sealed;

public class Test {

    public static void main(String[] args) {

        SealedPerson person = new FinalStudent();

        test(person);
    }

    static void test(SealedPerson person){

        if(person instanceof FinalStudent p){
            System.out.println(p);
        }
    }
}
