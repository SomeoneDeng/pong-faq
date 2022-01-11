package me.rustynail.pong.faq.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import me.rustynail.pong.faq.entity.Category;
import me.rustynail.pong.faq.vo.CategoryVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author dengqn
 * @date 2022/1/11 14:28
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
    List<CategoryVO> getList();
}
