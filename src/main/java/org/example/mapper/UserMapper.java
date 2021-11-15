package org.example.mapper;

import org.example.domain.User;

/**
 * @author 封心
 */
public interface UserMapper {

  /**
   * 根据id获取用户信息
   * @param id
   */
  User getUserById(String id);


  int saveUser(User user);
}
