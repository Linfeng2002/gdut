package com.gdut.backend.service;

import com.gdut.backend.common.Result;
import com.gdut.backend.po.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 杰哥
 * @since 2023-02-02
 */
public interface IUserService extends IService<User> {
    Result login(User user);
    Result logout();
}
