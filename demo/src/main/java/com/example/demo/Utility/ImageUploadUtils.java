package com.example.demo.Utility;

import java.util.regex.Pattern;

import org.springframework.web.multipart.MultipartFile;

public class ImageUploadUtils {
    private static final int MAX_FILENAME_LENGTH = 100;
    
    private static final Pattern SAFE_FILENAME_PATTERN = Pattern.compile("^[a-zA-Z0-9._-]+$");

    //file name is not blank , too long or contains illegal charters
    public static void validateFileName(MultipartFile file) {
        String filename = file.getOriginalFilename();

        if (filename == null || filename.isBlank()) {
            throw new IllegalArgumentException("Filename must not be empty");
        }

        if (filename.length() > MAX_FILENAME_LENGTH) {
            throw new IllegalArgumentException("Filename is too long");
        }

        if (!SAFE_FILENAME_PATTERN.matcher(filename).matches()) {
            throw new IllegalArgumentException("Filename contains invalid characters");
        }

        if (file.getSize() > 21 * 1024 * 1024) {
            throw new IllegalArgumentException("File is to big");
        }

        checkFileExtension(file.getContentType());
    }

    public static void checkFileExtension(String file){
        if(!file.toLowerCase().equals("image/jpg") 
            && !file.toLowerCase().equals("image/jpeg") 
            && !file.toLowerCase().equals("image/png")){
                 throw new IllegalArgumentException("Only JPG, JPEG, and PNG files are allowed.");
                }

    }
}

