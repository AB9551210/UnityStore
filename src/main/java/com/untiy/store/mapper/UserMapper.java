package com.untiy.store.mapper;

import com.untiy.store.entity.User;
import org.apache.ibatis.annotations.Mapper;

//用户模块的持久层接口
@Mapper
public interface UserMapper {
    /**
     * 插入用户的数据
     * @param user 用户的数据
     * @return 返回受影响的行数，判断是否执行成功
     */
    Integer insert(User user);

    /**
     * 根据用户名查询用户数据
     * @param username 用户名
     * @return 匹配的用户数据，如果没有匹配的数据，则返回null
     */
    User findByUsername(String username);
}
