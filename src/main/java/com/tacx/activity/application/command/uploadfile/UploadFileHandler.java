package com.tacx.activity.application.command.uploadfile;

import com.tacx.activity.domain.Activity;
import com.tacx.activity.domain.ActivityService;
import com.tacx.activity.infrastructure.bus.RequestHandler;
import com.tacx.activity.port.ActivityFileReader;
import com.tacx.activity.port.FileStore;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.Reader;

@Slf4j
@AllArgsConstructor
@Component
public class UploadFileHandler implements RequestHandler<UploadFileCommand, UploadFileResponse> {

    private final ActivityFileReader activityFileReader;
    private final FileStore fileStore;
    private final ActivityService activityService;

    @Override
    @SneakyThrows
    public UploadFileResponse handle(UploadFileCommand uploadFileCommand) {
        log.info("Handling upload-file request");

        MultipartFile file = uploadFileCommand.getFile();
        String filePath = fileStore.save(file.getName(), file.getInputStream());

        try (Reader reader = fileStore.open(filePath)) {
            Activity activity = activityFileReader.read(reader);
            activityService.save(activity);
            return new UploadFileResponse();
        }
    }
}
