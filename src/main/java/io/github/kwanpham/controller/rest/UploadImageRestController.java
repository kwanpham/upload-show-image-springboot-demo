package io.github.kwanpham.controller.rest;

import io.github.kwanpham.utils.ImageUploadUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Created by https://github.com/kwanpham
 */
@RestController
public class UploadImageRestController {

    @PostMapping("/UploadImageRest")
    public ResponseEntity uploadToLocalFileSystem(@RequestParam("image") MultipartFile multipartFile) throws IOException {

        String imageName = multipartFile.getOriginalFilename();

        // Kiem tra ten dinh dang file co hop le hay khong
        if (!ImageUploadUtils.validateImage(imageName)) {
            return ResponseEntity.badRequest().body("This is not an image format !");
        }

        // Doi ten anh theo time upload , co the boi den dong nay neu khong can
        imageName = ImageUploadUtils.getFakeImageName(imageName);

        Path path = Paths.get(ImageUploadUtils.getFolderUpload().getAbsolutePath() + File.separator + imageName);
        Files.copy(multipartFile.getInputStream() , path);

        String imageUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/image/")
                .path(imageName)
                .toUriString();
        return ResponseEntity.ok(imageUri);
    }
}
