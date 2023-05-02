package com.gdut.backend.service;

import com.gdut.backend.mapper.QuestionMapper;
import com.gdut.backend.po.Question;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 杰哥
 * @since 2023-03-26
 */
public interface IQuestionService extends IService<Question> {



    boolean viewCount(String id);

    boolean commentCount(String id);

    boolean likeCount(String id);
}
