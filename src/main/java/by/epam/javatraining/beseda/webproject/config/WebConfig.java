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



//    @Bean
//	public ViewResolver getViewResolver() {
//		FreeMarkerViewResolver freeMarkerViewResolver = new FreeMarkerViewResolver();
//		freeMarkerViewResolver.setOrder(1);
//		freeMarkerViewResolver.setSuffix(".ftl");
//		freeMarkerViewResolver.setPrefix("");
//		return freeMarkerViewResolver;
//	}

//    @Bean
//    public FreeMarkerConfigurer freemarkerConfig() {
//        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
//        freeMarkerConfigurer.setTemplateLoaderPath("/views/");
//        return freeMarkerConfigurer;
//    }

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



//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/doLogin").setViewName("doLogin");
//    }


//    @Bean(name="jsConfig")
//    public InternalResourceViewResolver getInternalResourceViewResolverJS(){
//        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//        resolver.setPrefix("/js/");
//        resolver.setSuffix(".js");
//        return resolver;
//    }
//    
//    @Bean(name="cssConfig")
//    public InternalResourceViewResolver getInternalResourceViewResolverCSS(){
//        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//        resolver.setPrefix("/css/");
//        resolver.setSuffix(".css");
//        return resolver;
//    }
	
//    @Bean(name="propertiesConfig")
//    public InternalResourceViewResolver getInternalResourceViewResolverProperty(){
//        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//        resolver.setPrefix("/");
//        resolver.setSuffix(".properties");
//        return resolver;
//    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/css/**")
                .addResourceLocations("/css/");

        registry.addResourceHandler("/javascript/**")
                .addResourceLocations("/javascript/");
//
//        registry.addResourceHandler("/**")
//        .addResourceLocations("/");
    }

    public void addInterceptors (InterceptorRegistry registry) {
        registry.addInterceptor(new LocaleInterceptor());
        registry.addInterceptor(new CharsetSetter());
    }

//
//	@Bean
//	public LoggingAspect getLoggingAspect(){return new LoggingAspect();}

}
