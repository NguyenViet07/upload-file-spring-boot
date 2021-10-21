package com.upfileservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
@Slf4j
public class UpFileService {
    @Value("${upload.dir}")
    private String uploadPath;

    public String uploadFile(MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();
            File folder = new File(uploadPath);
            if(!folder.exists()) {
                folder.mkdir();
            }
            File fileSave = new File(uploadPath, fileName);
            System.out.println("PATH:  " + fileSave.getAbsolutePath());
            FileOutputStream fileOutputStream = new FileOutputStream(fileSave);
            fileOutputStream.write(file.getBytes());
            fileOutputStream.close();
            return "success";
        } catch (IOException e) {
            log.error("Có lỗi: " + e.getMessage());
        }
        return "failed";
    }
}
