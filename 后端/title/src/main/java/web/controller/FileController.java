package web.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pojo.Result;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * 文件管理控制器
 * 
 * @author Smart-doc
 * @since 1.0.0
 */
@RestController
@RequestMapping("/common")
@Slf4j
public class FileController {
    
    /**
     * 上传文件
     * 
     * @param file 要上传的文件
     * @return 结果
     */
    @PostMapping("/upload")
    public Result upload(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        File file_local = new File("云");
        try {
            String path = file_local.getAbsolutePath();
            new File(path).mkdirs();
            log.info("文件路径:: {}", path);
            String saveName = UUID.randomUUID().toString() + "." + originalFilename;
            file.transferTo(new File(path + "\\" + saveName));
            log.info("文件上传成功: {}", path + "\\" + saveName);
            return Result.success("云/" + saveName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 下载文件
     * 
     * @param fileName 文件名
     * @param response 响应对象
     */
    @GetMapping("/download")
    public void download(String fileName, HttpServletResponse response) {
        File file_local = new File("云");
        String path = file_local.getAbsolutePath() + "/" + fileName;
        try {
            File file = new File(path);
            if (!file.exists()) {
                throw new IllegalArgumentException(path + "文件不存在");
            }
            response.setContentType("application/octet-stream");
            String encodedFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8);
            response.setHeader("Content-Disposition", "attachment;filename=" + encodedFileName);
            try (FileInputStream fis = new FileInputStream(file)) {
                StreamUtils.copy(fis, response.getOutputStream());
                response.flushBuffer();
                log.info("文件下载成功: {}", path);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}