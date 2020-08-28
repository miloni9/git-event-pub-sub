package file;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class FileWriter {

    public static void writeFile(List<String> stringList, String filePath)  {
        Path path = null;
        try {
            path = FileTool.getPath(filePath,true);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (path != null) {

            try {
                Files.write(path, stringList, Charset.forName("UTF-8"), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
