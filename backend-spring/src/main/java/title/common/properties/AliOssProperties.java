package title.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * AliOSS配置属性类 - 用于读取阿里云OSS相关配置
 */
@Data
@Component
@ConfigurationProperties(prefix = "title")
public class AliOssProperties {
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
}