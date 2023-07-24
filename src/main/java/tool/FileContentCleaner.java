package tool;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileContentCleaner {

    public static void main(String[] args) {
        try {
            cleanFiles("// Generated at", "compare\\original", "compare\\cleaned\\original");
            cleanFiles("// Generated at", "compare\\ts-number", "compare\\cleaned\\ts-number");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void cleanFiles(String lineContentMark, String inputDir, String outputDir) throws IOException {
        File[] inputFiles = new File(inputDir).listFiles();

        long updatedFileNumber = 0;
        for (File inputFile : inputFiles) {
            File outputFile = new File(outputDir + "\\" + inputFile.getName());
            outputFile.createNewFile();
            if (cleanLines(lineContentMark, inputFile, outputFile) > 0) {
                updatedFileNumber++;
            }
        }
        System.out.println("Affected " + updatedFileNumber + " files.");
    }

    public static long cleanLines(String lineContentMark, File inputFile, File outputFile) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

        long lineNumber = 0;
        long deletedNumber = 0;
        String currentLine;

        System.out.println("In file " + inputFile.getName());
        while ((currentLine = reader.readLine()) != null) {
            // trim newline when comparing with lineToRemove
            if (currentLine.contains(lineContentMark)) {
                System.out.println("[" + deletedNumber + "] Found line[" + lineNumber + "] to remove: " + currentLine);
                deletedNumber++;
                continue;
            }
            writer.write(currentLine + System.getProperty("line.separator"));
            lineNumber++;
        }
        if (deletedNumber == 0) {
            System.out.println("Found no lines to remove");
        }
        writer.close();
        reader.close();
        return deletedNumber;
    }

}