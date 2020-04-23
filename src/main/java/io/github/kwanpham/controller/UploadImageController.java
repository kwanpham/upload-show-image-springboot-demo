package io.github.kwanpham.controller;

import io.github.kwanpham.utils.ImageUploadUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by https://github.com/kwanpham
 */
@Controller
public class UploadImageController {



    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/UploadImage")
    public String uploadImage(@RequestParam("image") MultipartFile multipartFile, Model model) throws IOException {

        String imageName = multipartFile.getOriginalFilename();

        // Kiem tra ten dinh dang file co hop le hay khong
        if (!ImageUploadUtils.validateImage(imageName)) {
            model.addAttribute("message", "This is not an image format !");
            return "result";
        }

        // Doi ten anh theo time upload , co the boi den dong nay neu khong can
        imageName = ImageUploadUtils.getFakeImageName(imageName);

        Path path = Paths.get(ImageUploadUtils.getFolderUpload().getAbsolutePath() + File.separator + imageName);
        Files.copy(multipartFile.getInputStream() , path);
        model.addAttribute("imageName", imageName);
        model.addAttribute("message", "Upload Image Success!");

        return "result";
    }



}
