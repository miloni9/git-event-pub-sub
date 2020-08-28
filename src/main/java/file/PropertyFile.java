package file;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Properties;

public class PropertyFile {

    public static File writePropertyFile(Properties properties, String filePath) throws Exception {
        Path path = FileTool.getPath(filePath, true);
        OutputStream fileOut = Files.newOutputStream(path, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        properties.store(fileOut, "PropertiesFile");
        fileOut.close();
        return path.toFile();
    }

    public static File updatePropertyFile(Properties properties, String filePath) throws Exception {
        Properties pExists = readPropertyFile(filePath, true);
        properties.forEach(pExists::put);
        return writePropertyFile(pExists, filePath);
    }

    public static Properties readPropertyFile(String filePath) throws Exception {
        return readPropertyFile(filePath, false);
    }

    public static Properties readPropertyFile(String filePath, boolean create) throws Exception {
        Path path = FileTool.getPath(filePath, create);
        try (InputStream props = Files.newInputStream(path, StandardOpenOption.READ)) {
            Properties properties = new Properties();
            properties.load(props);
            return properties;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
