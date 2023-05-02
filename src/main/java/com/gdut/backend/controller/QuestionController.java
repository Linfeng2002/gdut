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
    public Result page(@RequestParam String id, Model model){
        Long questionId = null;
        try {
            questionId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new CustomizeException(CustomizeErrorCode.INVALID_INPUT);
        }
        Question question = questionService.getById(questionId);
        List<Comment> comments=commentService.getByParentId(String.valueOf(questionId),1);
        questionService.viewCount(id);
        model.addAttribute("question",question);
        model.addAttribute("comment",comments);
        return Result.success();
    }
    //新增
    @RequestMapping ("/save")
    public Result save(@RequestBody Question question){



        if(question.getGmtCreat()==null){
            String time= LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
            question.setGmtCreat(LocalDateTime.parse(time));
        }

        return questionService.save(question)?Result.success():Result.fail();
    }
    //更新
    @RequestMapping("/update")
    public Result update(@RequestBody Question question){return questionService.updateById(question)?Result.success():Result.fail();}
    //删除
    @RequestMapping("/delete")
    public Result del(@RequestParam String id){
        return questionService.removeById(id)?Result.success():Result.fail();
    }
    //游览数增加
    @RequestMapping("/viewCount")
    public Result viewCount(@RequestParam String id){
       return questionService.viewCount(id)?Result.success():Result.fail();
    }
    //点赞数增加
    @RequestMapping("/likeCount")
    public Result likeCount(@RequestParam String id){
        return questionService.likeCount(id)?Result.success():Result.fail();
    }
    //评论数增加
    @RequestMapping("/commentCount")
    public Result commentCount(@RequestParam String id){
        return questionService.commentCount(id)?Result.success():Result.fail();
    }
}
