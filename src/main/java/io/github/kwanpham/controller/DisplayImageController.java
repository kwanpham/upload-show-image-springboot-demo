package io.github.kwanpham.controller;

import io.github.kwanpham.utils.ImageUploadUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by https://github.com/kwanpham
 */
@Controller
public class DisplayImageController {

    @GetMapping("/image/{imageName}")
    public void showImage(@PathVariable("imageName") String imageName , HttpServletResponse response) throws IOException {
        // Truyen du lieu cho response voi java >= 1.7
        Files.copy(Paths.get(UploadImageController.DOWNLOAD_PATH+ File.separator+imageName), response.getOutputStream());
    }
}
