package io.github.kwanpham.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import static io.github.kwanpham.controller.UploadImageController.DOWNLOAD_PATH;

/**
 * Created by https://github.com/kwanpham
 */
public class ImageUploadUtils {

    public static boolean validateImage(String imageName) {
        {
            String[] s = imageName.split("\\.");
            if (s.length==0){
                return false;
            }
            String ext = s[s.length-1];
            switch (ext)
            {
                case "gif":
                    return true;
                case "png":
                    return true;
                case "jpg":
                    return true;
                case "jpeg":
                    return true;
                default:
                    return false;
            }
        }
    }

    public static String getFakeImageName(String imageName) {
        String[] s = imageName.split("\\.");
        String ext = s[s.length-1];
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSSS");
        imageName = formatter.format(new Date()) + "." + ext;
        return imageName;
    }


    public static File getFolderUpload() {
        File folderUpload = new File(DOWNLOAD_PATH);
        if (!folderUpload.exists()) {
            folderUpload.mkdirs();
        }
        return folderUpload;
    }
}
