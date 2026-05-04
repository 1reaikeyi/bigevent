package web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import web.utils.AliOssUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/oss")
@Slf4j
public class FileOss {

    @Autowired
    private AliOssUtil aliOssUtil;

    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> uploadFile(@RequestParam("file") MultipartFile file) {

        Map<String, String> result = new HashMap<>();
        
        if (file.isEmpty()) {
            result.put("error", "请选择要上传的文件");
            return ResponseEntity.badRequest().body(result);
        }

        try {
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String objectName = UUID.randomUUID().toString() + extension;
            
            String url = aliOssUtil.uploadFile(objectName, file.getInputStream());
            result.put("url", url);
            result.put("filename", originalFilename);
            log.info("url:{}", url);
            log.info("filename:{}", originalFilename);

            return ResponseEntity.ok(result);
        } catch (IOException e) {
            result.put("error", "文件上传失败: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }

    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadFile(@RequestParam("url") String fileUrl) {
        try {
            URL url = new URL(fileUrl);
            URLConnection connection = url.openConnection();
            InputStream inputStream = connection.getInputStream();
            
            byte[] bytes = inputStream.readAllBytes();
            inputStream.close();

            String filename = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
            String decodedFilename = java.net.URLDecoder.decode(filename, "UTF-8");

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + 
                    new String(decodedFilename.getBytes("UTF-8"), "ISO-8859-1") + "\"");
            headers.add(HttpHeaders.CONTENT_TYPE, connection.getContentType());

            return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
