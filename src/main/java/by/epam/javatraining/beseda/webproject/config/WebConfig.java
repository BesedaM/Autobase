package by.epam.javatraining.beseda.webproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
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
		configurer.setTemplateLoaderPaths("/view/", "/view/register/");
		return configurer;
	}
}
