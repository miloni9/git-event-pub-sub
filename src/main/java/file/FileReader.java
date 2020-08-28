package file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileReader {

    /*public static void main(String[] args) {
        List<String> fileLines = readFile("/home/niraj/Workspace/data-files/macs.txt", false);
        fileLines.forEach(System.out::println);
    }*/

    public static List<String> readFile(String filePath, boolean createFile) {
        Path path = null;
        List<String> lineStrings = new ArrayList<>();
        try {
            path = FileTool.getPath(filePath, createFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (path != null) {
            try (Stream<String> stream = Files.lines(path)) {
                lineStrings = stream.collect(Collectors.toList());
                //stream.forEach(lineStrings::add);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return lineStrings;
    }

}
