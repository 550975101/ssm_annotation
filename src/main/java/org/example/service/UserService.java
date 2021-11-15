package org.example.service;

import org.example.domain.User;

/**
 * @author by 封心
 * @classname UserService
 * @description TODO
 * @date 2021/11/15 10:08
 */
public interface UserService {

  /**
   * 保存数据
   * @param user
   * @return
   */
  int saveUser(User user);
}
