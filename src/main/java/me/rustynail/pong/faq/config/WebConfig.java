package me.rustynail.pong.faq.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * webmvc 配置
 *
 * @author dengqn
 * @date 2021/7/19 17:07
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private EscapeStringConverter escapeStringConverter;

    /**
     * 在参数绑定时，自定义String->String的转换器，
     * 在转换逻辑中对参数值进行转义，从而达到防XSS的效果
     *
     * @param registry
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(escapeStringConverter);
    }


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //设置允许跨域的路径
        registry.addMapping("/**")
                //设置允许跨域请求的域名
                .allowedOriginPatterns("*")
                //是否允许证书 不再默认开启
                .allowCredentials(true)
                //设置允许的方法
                .allowedMethods("*")
                //跨域允许时间
                .maxAge(3600);
    }
}
