package interview;

import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyTask {

    public static void main(String[] args) {
        System.out.println("Hello");

    }


    static Map<String, Integer> frequency(String text) {

        String[] words = text.split("-");

        Map<String, Integer> result = new HashMap();
        for (String word : words) {
            Integer currentNumber = 1;
            if (result.containsKey(word)) {
                currentNumber = result.get(word) + 1;
            }
            result.put(word, currentNumber);
        }

        return result;

    }

    static Map<String, Integer> frequency2(String text) {

        String[] words = text.split("-");

        Set<String> unique = Arrays.stream(words).map(word -> word.toLowerCase(Locale.ROOT)).collect(Collectors.toSet());
        Map<String, Integer> result = new HashMap();
        for (String word : unique) {
            result.put(word, Collections.frequency(unique, word));
        }
        return result;
    }
}
