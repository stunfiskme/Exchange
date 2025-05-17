package com.example.demo.service;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Utility.ImageUploadUtils;

@Service
public class FileService {
    
public void checkFile(MultipartFile file) throws IOException {
    ImageUploadUtils.validateFileName(file);
}


}
