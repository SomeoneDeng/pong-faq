package me.rustynail.pong.faq.entity.admin;

import lombok.Data;

import java.io.Serializable;

/**
 * @author dqn
 * 2022/1/11 21:50
 */
@Data
public class LoginVO implements Serializable {
    private static final long serialVersionUID = -4095843555989446289L;

    private String username;
    private String password;
}
