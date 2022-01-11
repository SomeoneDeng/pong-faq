package me.rustynail.pong.faq.controller.admin;

import cn.hutool.core.util.HashUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import lombok.extern.slf4j.Slf4j;
import me.rustynail.pong.faq.entity.admin.LoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;

/**
 * 登录
 *
 * @author dqn
 * 2022/1/11 21:48
 */
@RestController
@RequestMapping("/admin/login")
@Slf4j
public class LoginController {

    @Value("${admin.username}")
    private String username;
    @Value("${admin.password}")
    private String password;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/")
    public String login(@RequestBody LoginVO vo) {
        if (StrUtil.isBlank(vo.getPassword())) {
            return "";
        }

        log.info("{}", bCryptPasswordEncoder.encode(password));

        if (!username.equals(vo.getUsername()) || !bCryptPasswordEncoder.matches(vo.getPassword(), password)) {
            return "";
        }

        Digester digester = new Digester(DigestAlgorithm.SHA1);
        String digestHex = digester.digestHex(username + password);
        return username + "." + digestHex;
    }


}
