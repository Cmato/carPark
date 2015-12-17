package cz.muni.fi.pa165.springmvc.config;

import cz.muni.fi.pa165.carpark.sampledata.CarParkWithSampleDataConfiguration;
import javax.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * The central Spring context and Spring MVC configuration.
 * 
 * @author xhubeny2
 */
@EnableWebMvc
@Configuration
@Import({CarParkWithSampleDataConfiguration.class})
@ComponentScan(basePackages = "cz.muni.fi.pa165.springmvc.controllers")
public class SpringMvcConfig extends WebMvcConfigurerAdapter{
    
    public final static Logger log = LoggerFactory.getLogger(SpringMvcConfig.class);
    
    public static final String TEXTS = "Texts";
    
    /**
     * Maps the main page to a specific view.
     * @param registry ViewControllerRegistry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        log.debug("Mapping url \'/\' to home view.");
        //registry.addViewController("/").setViewName("home/home");
        //registry.addViewController("/car").setViewName("car/index");
    }
    
    /**
     * Enables default Tomcat servlet that serves static files.
     * @param configurer
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer){
        log.debug("Enable default servlet for static files.");
        configurer.enable();
    }
    
    /**
     * Mapping from view names to JSP pages in WEB-INF/jsp directory.
     * @return View Resolver with specific prefix and sufix
     */
    @Bean
    public ViewResolver viewResolver(){
        log.debug("Registering JSP from /WEB-INF/jsf/ as view names.");
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
    
    /**
     * Localisation.
     * @return Message Source with texts in constant TEXTS
     */
    @Bean
    public MessageSource messageSource(){
        log.debug("Setting texts for localization.");
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename(TEXTS);
        return messageSource;
    }
    
    /**
     * Provides JSR-303 Validator.
     * @return JSR-303 Validator
     */
    @Bean
    public Validator validator(){
        log.debug("Registering JSR-303 validator.");
        return new LocalValidatorFactoryBean();
    }
}
