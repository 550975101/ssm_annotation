package org.example.config;

import com.github.pagehelper.PageInterceptor;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * @author by 封心
 * @classname ApplicationMybatis
 * @description TODO
 * @date 2021/11/12 9:38
 */
@Configuration
//    <!--引入properties文件-->
//    <bean id="placeholderConfigurer" class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer">
//        <property name="location" value="classpath:jdbc.properties"/>
//    </bean>
@PropertySource( name="jdbc.properties",value = "classpath:jdbc.properties",ignoreResourceNotFound=false,encoding="UTF-8")
//    <!-- 将mybatis实现的接口注入到spring容器中 -->
//    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
//        <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"/>
//        <property name="basePackage" value="org.example.mapper"/>
//    </bean>
@MapperScan("org.example.mapper")
@EnableTransactionManagement
public class ApplicationMybatis {


  /**
   * 设置db前缀 不然读取username 会把你电脑主机名 读取出来
   */
  @Value("${db.username}")
  private String user;
  @Value("${db.password}")
  private String pass;
  @Value("${db.url}")
  private String url;
  @Value("${db.driver}")
  private String driver;

  @Bean
  public DataSource dataSource() {
    BasicDataSource basicDataSource = new BasicDataSource();
    basicDataSource.setUrl(url);
    basicDataSource.setDriverClassName(driver);
    basicDataSource.setUsername(user);
    basicDataSource.setPassword(pass);
    return basicDataSource;
  }

  /**
   * 分页插件
   * @return
   */
  @Bean
  public PageInterceptor pageInterceptor() {
    PageInterceptor pageInterceptor = new PageInterceptor();
    Properties properties = new Properties();
    properties.setProperty("value", "true");
    pageInterceptor.setProperties(properties);
    return pageInterceptor;
  }

  @Bean
  public SqlSessionFactoryBean sqlSessionFactoryBean() throws IOException {
    //    <!--配置sqlSession-->
    //    <!-- spring和mybatis完美结合，不需要mybatis的配置映射文件-->
    //    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
    //        <property name="dataSource" ref="dataSource"/>
    //        <!--自动扫描mapping.xml文件-->
    //        <property name="mapperLocations" value="classpath:mapper/*.xml"/>
    //    </bean>
    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    sqlSessionFactoryBean.setDataSource(dataSource());
    //mybatis整合分页插件↓
    Interceptor[] plugins={pageInterceptor()};
    sqlSessionFactoryBean.setPlugins(plugins);
    //扫描mapper.xml
    PathMatchingResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
    Resource[] resources = resourcePatternResolver.getResources("mapper/*Mapper.xml");
    sqlSessionFactoryBean.setMapperLocations(resources);

    return sqlSessionFactoryBean;
  }


  @Bean
  public DataSourceTransactionManager dataSourceTransactionManager(){
    //    <!--事务管理器-->
    //    <bean id="txManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
    //        <property name="dataSource" ref="dataSource"/>
    //    </bean>
    DataSourceTransactionManager dataSourceTransactionManager=new DataSourceTransactionManager();
    dataSourceTransactionManager.setDataSource(dataSource());
    return dataSourceTransactionManager;
  }
}
