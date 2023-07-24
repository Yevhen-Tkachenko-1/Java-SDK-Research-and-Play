package tool;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class FileComparator {

    public static void main(String[] args) {

        try {
            //System.out.println(compareDirContents(args[0], args[1]));
            System.out.println(compareDirContents("compare\\cleaned\\original", "compare\\cleaned\\ts-number"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static boolean compareDirContents(String path1, String path2) throws IOException {
        File dir1 = new File(path1);
        File dir2 = new File(path2);

        Set<String> files1 = Arrays.stream(dir1.listFiles()).map(file -> file.getName()).collect(Collectors.toSet());
        Set<String> files2 = Arrays.stream(dir2.listFiles()).map(file -> file.getName()).collect(Collectors.toSet());
        if (files1.equals(files2)) {
            System.out.println("The same files (by file names)");
        } else {
            throw new RuntimeException("Different files:\n" + files1 + "\n" + files2);
        }
        for (String file : files1) {
            compareFiles(path1 + "\\" + file, path2 + "\\" + file);
        }
        System.out.println("The same files (by content)");
        return true;
    }

    static void compareFiles(String file1, String file2) throws IOException {
        if (Files.mismatch(Path.of(file1), Path.of(file2)) != -1) {
            throw new RuntimeException("Different files: " + file1 + " and " + file2);
        }
    }
}