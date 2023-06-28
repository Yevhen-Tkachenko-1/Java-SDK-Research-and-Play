package version.java17;

import java.util.random.RandomGeneratorFactory;
import java.util.stream.IntStream;

public class RandomGeneratorFactoryClass {

    public static void main(String[] args) {

        getPseudoInts("L128X1024MixRandom", 10).forEach(i -> System.out.println(i));
    }

    static IntStream getPseudoInts(String algorithm, int streamSize) {
        // returns an IntStream with size @streamSize of random numbers generated using the @algorithm
        // where the lower bound is 0 and the upper is 100 (exclusive)
        return RandomGeneratorFactory.of(algorithm)
                .create()
                .ints(streamSize, 0,100);
    }
}
