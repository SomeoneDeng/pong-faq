package me.rustynail.pong.faq.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 问题
 *
 * @author dengqn
 * @date 2022/1/11 14:22
 */
@Data
@TableName("question")
public class Question implements Serializable {
    private static final long serialVersionUID = 4023824459578812472L;
    private Long id;
    private String title;
    private Long cid;
    private String content;
    private String answer;
    private Date created;
}
