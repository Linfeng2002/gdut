package com.gdut.backend.mapper;

import com.gdut.backend.po.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 杰哥
 * @since 2023-04-30
 */
public interface CommentMapper extends BaseMapper<Comment> {

    List<Comment> getByParentId(@Param("id") String id, @Param("type") int type);

    boolean likeCount(Comment comment);

    boolean commentCount(Comment comment);
}
