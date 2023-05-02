package com.gdut.backend.service;

import com.gdut.backend.po.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 杰哥
 * @since 2023-04-30
 */
public interface ICommentService extends IService<Comment> {

    List<Comment> getByParentId(String id,int type);
}
