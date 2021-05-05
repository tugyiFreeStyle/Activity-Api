package com.tacx.activity.application.command.uploadfile;

import com.tacx.activity.infrastructure.bus.Request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@AllArgsConstructor
public class UploadFileCommand implements Request {

    private MultipartFile file;
}
