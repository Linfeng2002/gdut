package com.gdut.backend.mapper;

import com.gdut.backend.po.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 杰哥
 * @since 2023-02-02
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
