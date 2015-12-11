package cz.muni.fi.pa165.springmvc.config;

import javax.servlet.Filter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Replaces web.xml file.
 * Extends the class {@link org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer}.
 *
 * @author xhubeny2
 */
public class StartInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * Creates spring context.
     * @return Spring context specified in class SpringMvcConfig.class.
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] {SpringMvcConfig.class};
    }
    
    /**
     * Specify @Configuration classes to be provided to the dispatcher servlet application context.
     * @return The configuration classes for the dispatcher servlet application context (may not be empty or null).
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }
    
    /**
     * Specify the servlet mapping(s) for the DispatcherServlet, e.g. '/', '/app', etc.
     * @return '/'
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
    
    /**
     * Maps filters to the dispatcher servlet.
     * @return Filter registration.
     */
    @Override
    protected Filter[] getServletFilters(){
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("utf-8");
        return new Filter[]{encodingFilter};
    }
}
