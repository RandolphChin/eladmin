package com.laboratory.rest;

import com.laboratory.storage.cloud.service.MinioStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.laboratory.domain.LocalStorage;

@RestController
@RequestMapping("/api/minio")
public class MinIoController {

    @Autowired
    MinioStorageService minioStorageService;

    @PostMapping(value = "/upload/{bucketName}")
    public ResponseEntity<Object> upload(@RequestParam("file") MultipartFile file, @PathVariable String bucketName) throws Exception {
        LocalStorage localStorage = minioStorageService.upload(bucketName,file.getOriginalFilename(), file);
        return new ResponseEntity<>(localStorage, HttpStatus.OK);
    }
}
