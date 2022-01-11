package me.rustynail.pong.faq.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.rustynail.pong.faq.entity.Category;
import me.rustynail.pong.faq.entity.Question;
import me.rustynail.pong.faq.mapper.CategoryMapper;
import me.rustynail.pong.faq.mapper.QuestionMapper;
import me.rustynail.pong.faq.vo.QuestionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;

/**
 * @author dengqn
 * @date 2022/1/11 14:30
 */
@RestController
@RequestMapping("/api/question")
public class QuestionController {


    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private CategoryMapper categoryMapper;


    /**
     * get questions paged
     *
     * @param cid
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/")
    public IPage<QuestionVO> page(
            @RequestParam(value = "search", defaultValue = "") String search,
            @RequestParam(value = "cid", defaultValue = "-1") Long cid,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size) {
        LambdaQueryWrapper<Question> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(Question::getCreated);
        if (cid != -1) {
            wrapper.eq(Question::getCid, cid);
        }

        if (StrUtil.isNotBlank(search)) {
            wrapper.and(query -> query.like(Question::getTitle, search)
                    .or().like(Question::getContent, search)
                    .or().like(Question::getAnswer, search));
        }


        return questionMapper.selectPage(new Page<>(page, size), wrapper)
                .convert(question -> {
                    QuestionVO questionVO = new QuestionVO();
                    BeanUtil.copyProperties(question, questionVO);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    questionVO.setCreated(dateFormat.format(question.getCreated()));

                    Category category = categoryMapper.selectById(questionVO.getCid());
                    if (category != null) {
                        questionVO.setCategory(category.getName());
                    }

                    return questionVO;
                });
    }

}
