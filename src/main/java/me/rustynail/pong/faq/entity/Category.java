package me.rustynail.pong.faq.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 分类
 *
 * @author dengqn
 * @date 2022/1/11 14:28
 */
@Data
@TableName("category")
@AllArgsConstructor
@NoArgsConstructor
public class Category implements Serializable {
    private static final long serialVersionUID = -8635051246162280421L;

    private Long id;

    private String name;
}
