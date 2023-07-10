package com.gdut.backend.controller;

import com.gdut.backend.common.Result;
import com.gdut.backend.exception.CustomizeErrorCode;
import com.gdut.backend.exception.CustomizeException;
import com.gdut.backend.po.Comment;
import com.gdut.backend.po.Question;
import com.gdut.backend.service.impl.CommentServiceImpl;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 杰哥
 * @since 2023-04-30
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Resource
    private CommentServiceImpl commentService;

    @RequestMapping("/query")
    public Result query(@RequestParam  String id){
        if (commentService.getById(id)==null) throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);

        List<Comment> comments=commentService.getByParentId(id,2);

        return Result.success(comments);
    }

    @RequestMapping("/save")
    public Result save(@RequestBody Comment comment){
        if(comment.getDiscription()==null) throw new CustomizeException(CustomizeErrorCode.CONTENT_IS_EMPTY);
        if(comment.getId()==null)throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);

        if(comment.getGmtCreat()==null) {
            String time= LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
            comment.setGmtCreat(LocalDateTime.parse(time));
        }
        return commentService.save(comment)?Result.success():Result.fail();
    }
    @RequestMapping("/update")
    public Result update(@RequestBody Comment comment){
        return commentService.updateById(comment)?Result.success():Result.fail();
    }
    @RequestMapping("/delete")
    public Result delete(@RequestParam String id) {
        return commentService.removeById(id)?Result.success():Result.fail();
    }
    @RequestMapping("/likeCount")
    public Result likeCount(@RequestParam String id){
        return commentService.likeCount(id);
    }
    @RequestMapping("/commentCount")
    public Result viewCount(@RequestParam String id){
        return commentService.commentCount(id);
    }

}
