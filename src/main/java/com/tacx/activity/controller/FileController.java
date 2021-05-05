package com.tacx.activity.controller;

import com.tacx.activity.application.command.uploadfile.UploadFileCommand;
import com.tacx.activity.infrastructure.bus.Bus;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/files")
public class FileController {

    private final Bus bus;

    @PostMapping("/upload")
    public void upload(@RequestParam("file") MultipartFile file) {
         bus.execute(new UploadFileCommand(file));
    }
}
