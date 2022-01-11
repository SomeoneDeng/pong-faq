package me.rustynail.pong.faq.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import me.rustynail.pong.faq.entity.Question;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author dengqn
 * @date 2022/1/11 14:28
 */
@Mapper
public interface QuestionMapper extends BaseMapper<Question> {
}
