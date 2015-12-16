package cz.muni.fi.pa165.service.config;

import cz.muni.fi.pa165.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.facade.CarFacadeImpl;
import cz.muni.fi.pa165.facade.EmployeeFacadeImpl;
import cz.muni.fi.pa165.facade.RentalFacadeImpl;
import cz.muni.fi.pa165.facade.ReservationFacadeImpl;
import cz.muni.fi.pa165.service.CarServiceImpl;
import cz.muni.fi.pa165.service.EmployeeServiceImpl;
import cz.muni.fi.pa165.service.RentalServiceImpl;
import cz.muni.fi.pa165.service.ReservationServiceImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * @author xcmarko
 */
@Configuration
@Import(PersistenceSampleApplicationContext.class)
@ComponentScan(basePackageClasses = { CarServiceImpl.class, CarFacadeImpl.class, EmployeeServiceImpl.class,
        EmployeeFacadeImpl.class, RentalServiceImpl.class, RentalFacadeImpl.class, ReservationServiceImpl.class,
        ReservationFacadeImpl.class })
public class MappingConfiguration {

    @Bean
    public Mapper dozer() {
        DozerBeanMapper mapper = new DozerBeanMapper();
        mapper.addMapping(new EntityDTOMapping());
        return mapper;
    }
}
