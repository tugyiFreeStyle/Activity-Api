package com.tacx.activity.port.adapter;

import com.tacx.activity.Application;
import com.tacx.activity.port.Clock;
import com.tacx.activity.port.CopyFile;
import com.tacx.activity.port.FileStore;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.InputStream;
import java.io.Reader;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@Component
public class FileStoreImpl implements FileStore {

    private final Clock clock;
    private final CopyFile copyFile;

    private static final String FOLDER_PATH = "uploads";

    @Override
    @SneakyThrows
    public String save(String name, InputStream inputStream) {

        var date = clock.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss-"));
        var fileName = date.concat(name);
        var uploadPath = Application.class
                .getClassLoader().getResource(FOLDER_PATH).getPath();

        var filePath = uploadPath.concat("/").concat(fileName);

        copyFile.apply(inputStream, Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
        return filePath;
    }

    @Override
    @SneakyThrows
    public Reader open(String path) {
        return new FileReader(path);
    }
}
