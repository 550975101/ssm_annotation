package org.example.controller;

import org.example.domain.User;
import org.example.mapper.UserMapper;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author by 封心
 * @classname UserController
 * @description TODO
 * @date 2021/11/12 9:25
 */
@Controller
public class UserController {


  @Autowired
  private UserMapper userMapper;

  @Autowired
  private UserService userService;

  @RequestMapping("/")
  public ModelAndView index() {
    User userById = userMapper.getUserById("1");
    System.out.println(userById);
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("index");
    return modelAndView;
  }

  @RequestMapping("/save")
  public ModelAndView save() {
    User user = new User();
    user.setName("你是");
    user.setRole_id("111");
    userService.saveUser(user);
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("index");
    return modelAndView;
  }
}
