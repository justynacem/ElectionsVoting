package com.app.utils;

import com.app.exceptions.ExceptionCode;
import com.app.exceptions.MyException;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class FileManager {

    private final static String IMAGE_PATH = "D:\\ProgramowanieKM\\MyProjects\\SpringBootElectionsManagement\\src\\main\\resources\\static\\img\\";

    private String createFilename(MultipartFile file) {
        try {
            final String[] fileInfo = file.getOriginalFilename().split("\\.");
            final String extension = fileInfo[fileInfo.length - 1];
            final String filename = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss"));
            return filename + "." + extension;
        } catch (Exception e) {
            throw new MyException(ExceptionCode.FILE, "CREATE FILENAME: " + e);
        }
    }

    public String addFile(MultipartFile file) {
        try {
            if (file == null || file.getBytes().length == 0) {
                throw new IllegalAccessException("FILE IS NOT CORRECT");
            }
            final String fileName = createFilename(file);
            final String fullPath = IMAGE_PATH + fileName;
            FileCopyUtils.copy(file.getBytes(), new File(fullPath));
            return fileName;
        } catch (Exception e) {
            throw new MyException(ExceptionCode.FILE, "ADD FILE: " + e);
        }
    }

    public boolean removeFile(String fileName) {
        try {
            if (fileName == null) {
                throw new NullPointerException("FILE NAME IS NULL");
            }
            return new File(fileName).delete();
        } catch (Exception e) {
            throw new MyException(ExceptionCode.FILE, "REMOVE FILE: " + e);
        }
    }

    public String updateFile(MultipartFile file, String fileName) {
        try {
            if (fileName == null) {
                throw new NullPointerException("FILE NAME IS NULL");
            }
            if (file == null || file.getBytes().length == 0) {
                return fileName;
            }
            final String fullPath = IMAGE_PATH + fileName;
            FileCopyUtils.copy(file.getBytes(), new File(fullPath));
            return fileName;
        } catch (Exception e) {
            throw new MyException(ExceptionCode.FILE, "UPDATE FILE: " + e);
        }
    }
}