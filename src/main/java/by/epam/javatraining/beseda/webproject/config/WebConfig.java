package by.epam.javatraining.beseda.webproject.config;

import by.epam.javatraining.beseda.webproject.interceptors.CharsetSetter;
import by.epam.javatraining.beseda.webproject.interceptors.LocaleInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "by.epam.javatraining.beseda.webproject.controller")
public class WebConfig implements WebMvcConfigurer {
//
//	@Bean
//	public ViewResolver getViewResolver() {
//		FreeMarkerViewResolver freeMarkerViewResolver = new FreeMarkerViewResolver();
//		freeMarkerViewResolver.setOrder(1);
//		freeMarkerViewResolver.setSuffix(".jsp");
//		freeMarkerViewResolver.setPrefix("");
//		return freeMarkerViewResolver;
//	}

	
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

	@Bean
	public FreeMarkerConfigurer getFreeMarkerConfigurer() {
		FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
		configurer.setTemplateLoaderPaths("/view/");
		return configurer;
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



}
