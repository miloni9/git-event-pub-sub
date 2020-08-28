package file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static util.RandomStringGenerator.UUIDString;

public class FileTool {

    public static void main(String[] args) {
        try {
            Files.list(Paths.get(".")).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> macList = new ArrayList<>();
        IntStream.range(0, 20000).forEach(i-> {macList.add(UUIDString());});
        FileWriter.writeFile(macList,"data-files/macId.txt");
    }
    public static Path getPath(String filePath) throws IOException, InvalidPathException {
        try {
            Path path = Paths.get(filePath);
            if (Files.notExists(path)) {
                return Files.createFile(path);
            }
            return path;

        } catch (IOException e){
            e.printStackTrace();
            throw new IOException();
        } catch (InvalidPathException t) {
            t.printStackTrace();
            throw new InvalidPathException(filePath, "Wrong URL");
        }
    }

    public static Path getPath(String filePath, boolean createFile) throws Exception {
        Path path = Paths.get(filePath);
        if (Files.notExists(path) && !createFile) {
            throw new Exception("File not present");
        } else if (Files.notExists(path)) {
            String last = filePath.substring(0,filePath.lastIndexOf(File.separator) + 1);
            if (!last.isEmpty()){
                Files.createDirectories(Paths.get(last));
            }
            return Files.createFile(path);
        }
        return path;
    }

    public static byte[] getFileBytes(String filePath, boolean createFile) throws java.nio.file.NoSuchFileException, Exception {
        Path path = Paths.get(filePath);
        if (Files.notExists(path) && !createFile) {
            throw new Exception("File not present");
        } else if (Files.notExists(path)) {
            try{
                Files.createFile(path);
            } catch (java.nio.file.NoSuchFileException e){
                throw  new java.nio.file.NoSuchFileException("");
            }

        }

        return Files.readAllBytes(path);
    }

}
