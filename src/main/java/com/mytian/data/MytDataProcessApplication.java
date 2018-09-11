package com.mytian.data;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created with IntelliJ IDEA.
 *
 * @author zhiqiang.tao
 * @author: zhiqiang.tao
 * Description:数据处理
 */

@Log4j2
@ComponentScan
@EnableScheduling
@SpringBootApplication(scanBasePackages = "com.mytian.data")
public class MytDataProcessApplication extends WebMvcConfigurerAdapter {

    @Override

    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

        configurer.favorPathExtension(false);

    }

    public static void main(String[] args) {
        SpringApplication.run(MytDataProcessApplication.class, args);
        log.info("spring-boot服务已经启动");
    }
}
