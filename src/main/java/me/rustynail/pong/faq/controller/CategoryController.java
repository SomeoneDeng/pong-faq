package me.rustynail.pong.faq.controller;

import cn.hutool.core.lang.Snowflake;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import me.rustynail.pong.faq.entity.Category;
import me.rustynail.pong.faq.mapper.CategoryMapper;
import me.rustynail.pong.faq.vo.CategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
    @Autowired
    private Snowflake snowflake;

    @GetMapping("/all")
    public List<CategoryVO> categories() {
        List<CategoryVO> list = categoryMapper.getList();
        list.forEach(l -> Optional.ofNullable(categoryMapper.selectById(l.getId()))
                .ifPresent(op -> l.setName(op.getName())));
        return list;
    }


    @PutMapping("/")
    public String update(@RequestBody CategoryVO vo) {
        Category category = categoryMapper.selectById(vo.getId());
        category.setName(vo.getName());
        categoryMapper.updateById(category);
        return "ok";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        categoryMapper.deleteById(id);
        return "ok";
    }

    @PostMapping("/")
    public String add(@RequestBody CategoryVO vo) {
        Long count = categoryMapper.selectCount(new LambdaQueryWrapper<Category>().eq(Category::getName, vo.getName()));
        if (count > 0) throw new RuntimeException("已有相同名字");
        Category category = new Category(snowflake.nextId(), vo.getName());
        categoryMapper.insert(category);
        return "";
    }

}
