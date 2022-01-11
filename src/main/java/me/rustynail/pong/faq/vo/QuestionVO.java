package me.rustynail.pong.faq.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author dengqn
 * @date 2022/1/11 14:55
 */
@Data
public class QuestionVO implements Serializable {
    private static final long serialVersionUID = 7811084902850066790L;
    private Long id;
    private String title;
    private Long cid;
    private String category;
    private String content;
    private String answer;
    private String created;
}
