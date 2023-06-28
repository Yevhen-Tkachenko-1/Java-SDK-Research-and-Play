package version.java11;

import java.util.Arrays;
import java.util.List;

public class CollectionsToArrayMethod {

    public static void main(String[] args) {

        List<String> sampleList = Arrays.asList("Java", "Kotlin");
        String[] sampleArray = sampleList.toArray(CollectionsToArrayMethod::generator);
        System.out.println(sampleArray[0]);
        System.out.println(sampleArray[1]);
    }

    static String[] generator(int i){ // always i==0
        return new String[i];
    }
}
