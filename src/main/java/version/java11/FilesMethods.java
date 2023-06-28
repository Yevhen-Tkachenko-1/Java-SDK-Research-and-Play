package version.java11;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FilesMethods {

    public static void main(String[] args) throws IOException {

        new File("test_output").mkdir();
        Path path = Files.createTempFile(Path.of("test_output"), "java11_files_methods", ".txt");
        Path filePath = Files.writeString(path, "Sample text");
        String fileContent = Files.readString(filePath);
        System.out.println(fileContent);
    }
}
