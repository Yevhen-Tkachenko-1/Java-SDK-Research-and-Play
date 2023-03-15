package java12;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileMismatchMethod {

    public static void main(String[] args) throws IOException {

        new File("test_output").mkdir();
        Path test_output = Path.of("test_output");

        Path filePath1 = Files.createTempFile(test_output,"java12_file_mismatch_method", ".txt");
        Path filePath2 = Files.createTempFile(test_output,"java12_file_mismatch_method", ".txt");
        Path filePath3 = Files.createTempFile(test_output,"java12_file_mismatch_method", ".txt");

        Files.writeString(filePath1, "Java 12 Article");
        Files.writeString(filePath2, "Java 12 Article");
        Files.writeString(filePath3, "Java 12 Topic");

        System.out.println(Files.mismatch(filePath1, filePath2));
        System.out.println(Files.mismatch(filePath1, filePath3));
    }
}
