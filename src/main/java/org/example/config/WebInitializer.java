package org.example.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author by 封心
 * @classname WebInitializer
 * @description TODO
 * @date 2021/11/12 9:52
 */
//代替web.xml
public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
  @Override
  protected Class<?>[] getRootConfigClasses() {
    return new Class<?>[]{ApplicationMain.class};
  }

  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class<?>[]{ApplicationMvc.class};
  }

  @Override
  protected String[] getServletMappings() {
    return new String[]{"/"};
  }
}
