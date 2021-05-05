package com.tacx.activity.port.adapter;

import com.tacx.activity.port.CopyFile;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class FileSystem implements CopyFile {

    @Override
    @SneakyThrows
    public void apply(InputStream inputStream, Path path, CopyOption copyOption) {
        Files.copy(inputStream, path, copyOption);
    }
}
