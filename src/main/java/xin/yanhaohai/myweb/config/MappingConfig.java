package xin.yanhaohai.myweb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MappingConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //registry.addViewController("/index.html").setViewName("main");
        registry.addViewController("/myimage.html").setViewName("myimage");
        registry.addViewController("/main.html").setViewName("main");
        registry.addViewController("/list.html").setViewName("list");
        registry.addViewController("/about.html").setViewName("about");
        registry.addViewController("/daohang.html").setViewName("daohang");
        registry.addViewController("/info.html").setViewName("info");
    }
}
