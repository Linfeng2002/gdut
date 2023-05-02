package com.gdut.backend.mapper;

import com.gdut.backend.po.Question;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 杰哥
 * @since 2023-03-26
 */
public interface QuestionMapper extends BaseMapper<Question> {

    boolean viewCount(Question record);

    boolean likeCount(Question record);

    boolean commentCount(Question record);
}
