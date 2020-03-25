package by.epam.javatraining.beseda.webproject.config;

import by.epam.javatraining.beseda.webproject.aop.LoggingAspect;
import by.epam.javatraining.beseda.webproject.interceptors.CharsetSetter;
import by.epam.javatraining.beseda.webproject.interceptors.LocaleInterceptor;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {"by.epam.javatraining.beseda.webproject.controller"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Import({LoggingAspect.class})
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver bean = new InternalResourceViewResolver();
        bean.setOrder(1);
        bean.setPrefix("/view/");
        bean.setSuffix(".jsp");
        return bean;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/css/**")
                .addResourceLocations("/css/");

        registry.addResourceHandler("/javascript/**")
                .addResourceLocations("/javascript/");
    }

    public void addInterceptors (InterceptorRegistry registry) {
        registry.addInterceptor(new LocaleInterceptor());
        registry.addInterceptor(new CharsetSetter());
    }

}
