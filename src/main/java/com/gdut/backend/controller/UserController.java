package com.gdut.backend.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gdut.backend.common.QueryPageParam;
import com.gdut.backend.common.Result;
import com.gdut.backend.po.User;
import com.gdut.backend.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.gdut.backend.common.Result;

import javax.annotation.Resource;
import javax.jws.soap.SOAPBinding;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 杰哥
 * @since 2023-02-02
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;

    //全部分页查询
    @RequestMapping ("/listPage")
    public Result listPage(@RequestBody QueryPageParam query){
        HashMap param = query.getParam();
        String username = (String)param.get("username");

        Page<User> userPage = new Page();
//        userPage.setSize(-1);
//        userPage.setCurrent(-1);
        userPage.setSize(query.getPageSize());
        userPage.setCurrent(query.getPageNum());

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper() ;

        lambdaQueryWrapper.like(User::getUsername,username);

        IPage<User> page = userService.page(userPage,lambdaQueryWrapper);

        return Result.success(page.getTotal(),page.getRecords());
    }
    //单独查询
    @GetMapping("/query")
    public Result query(@RequestBody User user){

        String username=user.getUsername();

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper() ;
        if(StringUtils.isNotBlank(username) && !"null".equals(username)){
            lambdaQueryWrapper.eq(User::getUsername,username);
        }

            Object data=userService.list(lambdaQueryWrapper);
        return Result.success(data);
    }

    //根据用户名查找
    @RequestMapping("/findByNo")
    public Result findByNo(@RequestParam String no){
        List list = userService.lambdaQuery().eq(User::getUsername,no).list();
        return list.size()>0?Result.success(list):Result.fail();
    }
    //新增
    @RequestMapping ("/save")
    public Result save(@RequestBody User user){
        return userService.save(user)?Result.success():Result.fail();
    }
    //更新
    @RequestMapping("/update")
    public Result update(@RequestBody User user){return userService.updateById(user)?Result.success():Result.fail();}
    //删除
    @RequestMapping("/delete")
    public Result del(@RequestParam String id){
        return userService.removeById(id)?Result.success():Result.fail();
    }
    @RequestMapping("/login")
    public Result login(@RequestBody User user){return userService.login(user);}
    @RequestMapping("/logout")
    public Result logout(@RequestBody User user){return userService.logout();}
//    @PostMapping("/login")
//    public


}
