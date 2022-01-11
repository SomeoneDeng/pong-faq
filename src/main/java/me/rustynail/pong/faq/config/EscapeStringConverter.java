package me.rustynail.pong.faq.config;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HtmlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * xss处理
 *
 * @author dengqn
 * @date 2021/7/19 17:05
 */
@Component
public class EscapeStringConverter implements Converter<String, String> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public String convert(String source) {
        //        logger.info("source -> [{}], converted --> [{}]", source, converted);
        return StrUtil.isBlank(source) ? source : HtmlUtil.escape(source);
    }
}
