package com.tacx.activity.port;


import java.io.InputStream;
import java.nio.file.CopyOption;
import java.nio.file.Path;

@FunctionalInterface
public interface CopyFile {
    void apply(InputStream inputStream, Path path, CopyOption copyOption);
}
