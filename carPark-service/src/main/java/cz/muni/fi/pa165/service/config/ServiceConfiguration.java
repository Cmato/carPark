package cz.muni.fi.pa165.service.config;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import cz.muni.fi.pa165.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.dto.RentalDTO;
import cz.muni.fi.pa165.dto.ReservationDTO;
import cz.muni.fi.pa165.entities.Rental;
import cz.muni.fi.pa165.entities.Reservation;

/**
 *
 * @author xhubeny2
 */
@Configuration
@Import(PersistenceSampleApplicationContext.class)
@ComponentScan("cz.muni.fi.pa165")
//@ComponentScan(basePackageClasses={RentalServiceImpl.class, RentalFacadeImpl.class})
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
	        mapping(Rental.class, RentalDTO.class);
	        mapping(Reservation.class, ReservationDTO.class);
	    }
	}
    
}
