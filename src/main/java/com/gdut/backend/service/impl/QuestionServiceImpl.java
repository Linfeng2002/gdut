package com.gdut.backend.service.impl;

import com.gdut.backend.po.Question;
import com.gdut.backend.mapper.QuestionMapper;
import com.gdut.backend.service.IQuestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 杰哥
 * @since 2023-03-26
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements IQuestionService {

    @Resource
    private QuestionMapper questionMapper;

    @Override
    public boolean viewCount(String id) {
        Question record = new Question();
        record.setId(Integer.valueOf(id));
        record.setViewCount(1);
        return   questionMapper.viewCount(record);
    }

    @Override
    public boolean commentCount(String id) {
        Question record = new Question();
        record.setId(Integer.valueOf(id));
        record.setCommentCount(1);
        return questionMapper.commentCount(record);
    }

    @Override
    public boolean likeCount(String id) {
        Question record = new Question();
        record.setId(Integer.valueOf(id));
        record.setLikeCount(1);
        return questionMapper.likeCount(record);
    }
}
