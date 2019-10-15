package by.epam.javatraining.beseda.webproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "by.epam.javatraining.beseda.webproject.controller")
public class WebConfig implements WebMvcConfigurer {

	@Bean
	public ViewResolver getViewResolver() {
		FreeMarkerViewResolver freeMarkerViewResolver = new FreeMarkerViewResolver();
		freeMarkerViewResolver.setOrder(1);
		freeMarkerViewResolver.setSuffix(".html");
		freeMarkerViewResolver.setPrefix("");
		return freeMarkerViewResolver;
	}

	@Bean
	public FreeMarkerConfigurer getFreeMarkerConfigurer() {
		FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
		configurer.setTemplateLoaderPaths("/view/");
		return configurer;
	}
	
    @Bean(name="jsConfig")
    public InternalResourceViewResolver getInternalResourceViewResolverJS(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/js/");
        resolver.setSuffix(".js");
        return resolver;
    }
    
    @Bean(name="cssConfig")
    public InternalResourceViewResolver getInternalResourceViewResolverCSS(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/css/");
        resolver.setSuffix(".css");
        return resolver;
    }
	
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

        registry.addResourceHandler("/js/**")
                .addResourceLocations("/js/");
        
//        registry.addResourceHandler("/**")
//        .addResourceLocations("classpath:/");
    }
}
