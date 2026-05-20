package title.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * JWT配置属性类 - 用于读取JWT相关配置
 */
@Data
@ConfigurationProperties(prefix = "jwt")
@Component
public class JwtProperties {
    private String secretKey;
    private long ttlMillis;
    private String tokenName;
}