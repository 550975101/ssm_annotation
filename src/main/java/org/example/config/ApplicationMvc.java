package org.example.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author by 封心
 * @classname ApplicationMvc
 * @description TODO
 * @date 2021/11/12 9:30
 */
@Configuration
//<mvc:annotation-driven/> 开启注解驱动
@EnableWebMvc
//<context:component-scan base-package="org.example.controller"/>
@ComponentScan("org.example.controller")
public class ApplicationMvc extends WebMvcConfigurerAdapter {


  //配置视图解析器
  @Override
  public void configureViewResolvers(ViewResolverRegistry viewResolverRegistry) {
    //    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
    //        <!--重定向时是否加上上下文路径-->
    //        <property name="redirectContextRelative" value="true"/>
    //        <!--配置解析前后缀-->
    //        <property name="prefix" value="/WEB-INF/views/"/>
    //        <property name="suffix" value=".jsp"/>
    //    </bean>
    InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
    internalResourceViewResolver.setPrefix("/WEB-INF/views/");
    internalResourceViewResolver.setSuffix(".jsp");
    internalResourceViewResolver.setExposeContextBeansAsAttributes(true);
    viewResolverRegistry.viewResolver(internalResourceViewResolver);
  }

  //静态资源不拦截
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/js/**").addResourceLocations("/js/");
    registry.addResourceHandler("/css/**").addResourceLocations("/css/");
    registry.addResourceHandler("/static/**").addResourceLocations("/static/");
  }
}
