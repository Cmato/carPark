package cz.muni.fi.pa165.config;

import cz.muni.fi.pa165.carPark.PersistenceSampleApplicationContext;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 *
 * @author xhubeny2
 */
@Configuration
@Import(PersistenceSampleApplicationContext.class)
@ComponentScan(basePackages = "cz.fi.muni.pa165")
public class ServiceConfiguration {
    
    @Bean
	public Mapper dozer(){
		DozerBeanMapper dozer = new DozerBeanMapper();		
		dozer.addMapping(new DozerCustomConfig());
		return dozer;
	}
        
        public class DozerCustomConfig extends BeanMappingBuilder {
	    @Override
	    protected void configure() {
	        //mapping(Category.class, CategoryDTO.class);
	    }
	}
    
}
