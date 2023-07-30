package com.gdut.backend.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gdut.backend.common.QueryPageParam;
import com.gdut.backend.common.Result;
import com.gdut.backend.exception.CustomizeErrorCode;
import com.gdut.backend.exception.CustomizeException;
import com.gdut.backend.po.Comment;
import com.gdut.backend.po.Question;
import com.gdut.backend.po.User;
import com.gdut.backend.service.ICommentService;
import com.gdut.backend.service.IQuestionService;
import com.gdut.backend.service.impl.QuestionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 杰哥
 * @since 2023-03-26
 */
@RestController
@RequestMapping("/question")
public class QuestionController {
    @Resource
    private IQuestionService questionService;

    @Resource
    private ICommentService commentService;


    //分页查找所有问题
    @RequestMapping("/listPage")
    public Result listPage (@RequestBody QueryPageParam queryPageParam){
        HashMap map=queryPageParam.getParam();
        int id=(int)map.get("id");

        Page<Question> userPage = new Page();
        userPage.setSize(queryPageParam.getPageSize());
        userPage.setCurrent(queryPageParam.getPageNum());

        LambdaQueryWrapper<Question> objectLambdaQueryWrapper = new LambdaQueryWrapper<>();
        objectLambdaQueryWrapper.like(Question::getId,id);

        IPage<Question> result=questionService.page(userPage,objectLambdaQueryWrapper);

        return Result.success(result.getTotal(),result.getRecords());
    }

    //单个查询问题
    @RequestMapping("/query")
    public Result page(@RequestParam(value = "id") String id, Model model){
        Long questionId = null;
        try {
            questionId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new CustomizeException(CustomizeErrorCode.INVALID_INPUT);
        }
        questionService.viewCount(id);
        Question question = questionService.getById(questionId);
        List<Comment> comments = commentService.getByParentId(String.valueOf(questionId),1);
        model.addAttribute("question",question);
        model.addAttribute("comment",comments);
        return Result.success(model);
    }
    //新增
    @RequestMapping ("/save")
    public Result save(@RequestBody Question question){

        if(question.getDiscription()==null) return Result.fail(new CustomizeException(CustomizeErrorCode.CONTENT_IS_EMPTY) );
        if(question.getId()==null) return Result.fail(new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND ));

        if(question.getGmtCreat()==null){
            String time= LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
            question.setGmtCreat(LocalDateTime.parse(time));
        }

        return questionService.save(question)?Result.success():Result.fail();
    }
    //更新
    @RequestMapping("/update")
    public Result update(@RequestBody Question question){
        if(questionService.getById(question.getId())!=null)
        {
            questionService.updateById(question);
            return Result.success();
        }else {
            return Result.fail(new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND));
        }
    }
    //删除
    @RequestMapping("/delete")
    public Result del(@RequestParam(value = "id") String id){
        return questionService.removeById(id)?Result.success():Result.fail();
    }
    //游览数增加
    @RequestMapping("/viewCount")
    public Result viewCount(@RequestParam(value = "id") String id){
            if(questionService.getById(id)!=null)
            {
                  questionService.viewCount(id);
                  return Result.success();
            }else {
                return Result.fail(new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND));
            }
    }
    //点赞数增加
    @RequestMapping("/likeCount")
    public Result likeCount(@RequestParam(value = "id") String id){
        if(questionService.getById(id)!=null)
        {
            questionService.likeCount(id);
            return Result.success();
        }else {
            return Result.fail(new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND));
        }
    }
    //评论数增加
    @RequestMapping("/commentCount")
    public Result commentCount(@RequestParam(value = "id") String id){
        if(questionService.getById(id)!=null)
        {
            questionService.commentCount(id);
            return Result.success();
        }else {
            return Result.fail(new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND));
        }

    }
}
