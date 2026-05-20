package comitheima.Utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "aliyun.oss")//告诉SpringBoot将本类中的属性和配置文件中相关的属性进行绑定
public class AliyunOSSProperties {
    private String endpoint;
    private String bucketName;
    private String region;
    //将配置项的值赋给属性
}
