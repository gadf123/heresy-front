package com.heresy.utills;

import com.heresy.exceptions.StorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;

@Component
public class FileUploader {

    @Value("${file.upload.path}")
    String uploadPath;

    public String uploadSingleFile(MultipartFile file){
        String filename = System.currentTimeMillis()+StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                // This is a security check
                throw new StorageException(
                        "Cannot store file with relative path outside current directory "
                                + filename);
            }
            Path rootLocation = Paths.get(uploadPath);

            File folder = new File(rootLocation.toUri());

            if(!folder.exists()){
                folder.mkdir();
            }

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(
                        inputStream,
                        rootLocation.resolve(filename),
                        StandardCopyOption.REPLACE_EXISTING
                );
            }
            return filename;
        }
        catch (IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
        }

    }
}
