package com.untiy.store.mapper;

import com.untiy.store.entity.User;

//用户模块的持久层接口
public interface UserMapper {
    /**
     * 插入用户的数据
     * @param user 用户的数据
     * @return 返回受影响的行数，判断是否执行成功
     */
    Integer insert(User user);

    /**
     * 根据用户名来查询用户的数据
     * @param username 用户名
     * @return 如果找到对应的用户则返回用户的数据，否则返回null
     */
    User findByUsername(String username);
}
