package test.study.runleocat.malluser.conifg;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@Slf4j
public class WebMVCConfig  implements WebMvcConfigurer {


    @Override
    public void addViewControllers (ViewControllerRegistry registry) {
        log.info("进入mvc配置器");
        //registry.addViewController("/gotoList").setViewName("empList");
    }
}
