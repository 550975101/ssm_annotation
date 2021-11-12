package org.example.config;

import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author by 封心
 * @classname ApolicationMain
 * @description TODO
 * @date 2021/11/12 9:25
 */
@Configuration
//包扫描 排除controller RestController 交给mvc处理
//    <!--自动扫描-->
//    <context:component-scan base-package="org.example">
//        <context:exclude-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
//    </context:component-scan>
@ComponentScan(basePackages = "org.example",excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,value = {Controller.class, RestController.class})})
@EnableAspectJAutoProxy
@Import(ApplicationMybatis.class)
public class ApplicationMain {
}
