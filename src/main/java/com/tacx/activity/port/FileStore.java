package com.tacx.activity.port;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

public interface FileStore {

    String save(String name, InputStream inputStream) throws IOException;

    Reader open(String path);
}
