package com.upfileservice.controller;


import com.upfileservice.service.UpFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("/api")
public class UpFileController {
    @Autowired
    private UpFileService upFileService;

    @PostMapping("/upFileService")
    public String upFileService(@RequestParam("file") MultipartFile file) {
        return upFileService.uploadFile(file);
    }

    @GetMapping("/download")
    public Object download(@RequestParam("url") String url) {
        File file = new File(url);
        if (file.exists()) {
            try {
                byte[] value = Files.readAllBytes(file.toPath());
                HttpHeaders headers = new HttpHeaders();
                headers.set("Content-Disposition", "attachment; filename=" + url.split("/")[url.split("/").length - 1]);
                return ResponseEntity.status(HttpStatus.OK).headers(headers).body(value);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "File khong ton tai";
    }

    @GetMapping("/test")
    public String test() {
        return "TEST";
    }
}
