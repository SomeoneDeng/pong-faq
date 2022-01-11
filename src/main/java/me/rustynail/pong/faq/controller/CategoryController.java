package me.rustynail.pong.faq.controller;

import me.rustynail.pong.faq.mapper.CategoryMapper;
import me.rustynail.pong.faq.vo.CategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * @author dengqn
 * @date 2022/1/11 15:03
 */
@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryMapper categoryMapper;

    @GetMapping("/all")
    public List<CategoryVO> categories() {
        List<CategoryVO> list = categoryMapper.getList();
        list.forEach(l -> Optional.ofNullable(categoryMapper.selectById(l.getId()))
                .ifPresent(op -> l.setName(op.getName())));
        return list;
    }

}
