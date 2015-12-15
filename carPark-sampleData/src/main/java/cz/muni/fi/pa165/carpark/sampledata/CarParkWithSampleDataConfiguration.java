package cz.muni.fi.pa165.carpark.sampledata;

import cz.muni.fi.pa165.service.config.MappingConfiguration;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 *
 * @author xhubeny2
 */
@Configuration
@Import(MappingConfiguration.class)
@ComponentScan(basePackageClasses = {SampleDataLoadingFacadeImpl.class})
public class CarParkWithSampleDataConfiguration {
    
    final static Logger log = LoggerFactory.getLogger(CarParkWithSampleDataConfiguration.class);

    @Autowired
    SampleDataLoadingFacade sampleDataLoadingFacade;

    @PostConstruct
    public void dataLoading() {
        log.debug("Sample data loading...");
        sampleDataLoadingFacade.loadData();
    }
}
