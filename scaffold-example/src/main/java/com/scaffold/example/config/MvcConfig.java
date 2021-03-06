package com.scaffold.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

/**
 * Created by sungang on 2017/8/19.
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    /**
     * 不需要拦截处理的URI
     */
    public static final String[] IGNORE_URIS = {
            "/error/**",
            "/kaptcha/**",
            "/auth/**",
            "/login",
            "/signin/**",
            "/signup/**"
    };


    /**
     * 过滤资源
     */
    public static final String[] IGNORE_RESOURCES = {
            "/bower_components/**",
            "/**/*.css",
            "/**/*.png",
            "/**/*.gif",
            "/**/*.jpg",
            "/**/*.js"
    };


    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver resolver = new SessionLocaleResolver();
        resolver.setDefaultLocale(Locale.US);
        return resolver;
    }


    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }


    /**
     * AbstractApplicationContext.initMessageSource
     * 国际化bean必须定义成MESSAGE_SOURCE_BEAN_NAME
     *
     * @return
     */
    @Bean(name = "messageSource")
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
        resourceBundleMessageSource.setBasenames("i18n/message");
        resourceBundleMessageSource.setUseCodeAsDefaultMessage(true);
        resourceBundleMessageSource.setDefaultEncoding("UTF-8");
        return resourceBundleMessageSource;
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(localeChangeInterceptor());
    }

//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        super.addViewControllers(registry);
//        registry.addViewController("/").setViewName("index");
//    }


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("/page/login.html");
        registry.addViewController("/").setViewName("/page/index.html");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
       /* registry
                .addResourceHandler(IGNORE_RESOURCES)
                .addResourceLocations("/bower_components*//**")
         .setCachePeriod(31556926);*/
    }
}
