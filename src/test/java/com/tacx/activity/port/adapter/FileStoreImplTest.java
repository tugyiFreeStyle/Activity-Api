package com.tacx.activity.port.adapter;

import com.tacx.activity.port.CopyFile;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class FileStoreImplTest {


    @Test
    void should_save_fileStore() {
        //given
        var fileName = "test";
        var stream = new ByteArrayInputStream("stream".getBytes());
        var now = LocalDateTime.now();

        CopyFile noCopy = (inputStream, path, opt) -> {
            //doNothing
        };
        var fileStore = new FileStoreImpl(() -> now, noCopy);

        //when
        String path = fileStore.save(fileName, stream);

        //then
        assertThat(path).isNotBlank();
    }
}
