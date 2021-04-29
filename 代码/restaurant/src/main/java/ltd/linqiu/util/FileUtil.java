package ltd.linqiu.util;

import org.springframework.boot.system.ApplicationHome;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUtil {

    public static String save(MultipartFile file, String folderName) {
        //上传文件路径
        String path = new ApplicationHome(
                FileUtil.class).getSource().getParent() + File.separator + "upload" + File.separator + folderName + File.separator + new SimpleDateFormat(
                "yyyy-MM-dd").format(new Date());
        //上传文件名
        String filename = file.getOriginalFilename();
        if (filename == null) {
            return null;
        }
        File filePath = new File(path, filename);
        //判断路径是否存在，如果不存在就创建一个
        if (!filePath.getParentFile().exists()) {
            if (!filePath.getParentFile().mkdirs()) {
                return null;
            }
        }
        String fullPath = path + File.separator + filename;
        // 将上传文件保存到目标文件当中
        try {
            file.transferTo(new File(fullPath));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return path + File.separator + filename;
    }

}
