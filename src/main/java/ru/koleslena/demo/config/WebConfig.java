package ru.koleslena.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.mvc.WebContentInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Created by elenko on 23.06.15.
 */
@Configuration
@EnableWebMvc
@EnableSpringDataWebSupport
@EnableHypermediaSupport(type = { EnableHypermediaSupport.HypermediaType.HAL })
@ComponentScan(basePackages = "ru.koleslena.demo.controller")
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer c) {
        c.defaultContentType(MediaTypes.HAL_JSON);
    }

    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public WebContentInterceptor webContentInterceptor() {
        WebContentInterceptor interceptor = new WebContentInterceptor();
        interceptor.setCacheSeconds(0);
        interceptor.setUseExpiresHeader(true);
        interceptor.setUseCacheControlHeader(true);
        interceptor.setUseCacheControlNoStore(true);

        return interceptor;
    }

    /*@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/libs*//**").addResourceLocations("/libs/");
        registry.addResourceHandler("/app*//**").addResourceLocations("/app/");
        registry.addResourceHandler("/assets*//**").addResourceLocations("/assets/");
    }*/

    @Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**")
                        .addResourceLocations("/resources/");
	}

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(webContentInterceptor());
    }
}
