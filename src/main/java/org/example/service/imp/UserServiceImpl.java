package org.example.service.imp;

import org.example.domain.User;
import org.example.mapper.UserMapper;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author by 封心
 * @classname UserServiceImpl
 * @description TODO
 * @date 2021/11/15 10:16
 */
@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserMapper userMapper;

  @Override
  @Transactional(rollbackFor = Exception.class)
  public int saveUser(User user) {
    int i = userMapper.saveUser(user);
    int i1 = ThreadLocalRandom.current().nextInt(0, 2);
    i1 = 1 / i1;
    return i;
  }
}
