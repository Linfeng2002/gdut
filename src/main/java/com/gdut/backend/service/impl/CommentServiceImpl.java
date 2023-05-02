package com.gdut.backend.service.impl;

import com.gdut.backend.po.Comment;
import com.gdut.backend.mapper.CommentMapper;
import com.gdut.backend.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 杰哥
 * @since 2023-04-30
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

    @Resource
    private CommentMapper commentMapper;

    //根据父类id查找评论
    @Override
    public List<Comment> getByParentId(String id,int type) {
        commentMapper.getByParentId(id,type);
        return null;
    }
}
