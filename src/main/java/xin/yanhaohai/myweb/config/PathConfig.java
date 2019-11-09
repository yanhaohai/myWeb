package xin.yanhaohai.myweb.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
//读取配置文件
@PropertySource("classpath:path.properties")
public class PathConfig {
    @Value("${imagePath}")
    private String imagePath;
    @Value("${blogPath}")
    private String blogPath;
    public String getImagePath(){
        return imagePath;
    }

    public String getBlogPath() {
        return blogPath;
    }
}
