package cz.muni.fi.pa165.service;

import java.util.Collection;
import java.util.List;
import org.dozer.Mapper;

/**
 *
 * @author xcmarko
 */
public interface MappingService {   
    public Mapper getMapper();    
    public <T> T mapTo(Object objectToMap, Class<T> classToMapTo);
    public <T> List<T> mapToCollection(Collection<?> objects, Class<T> mapToClass);
}


