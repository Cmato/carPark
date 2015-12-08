package cz.muni.fi.pa165.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author xcmarko
 */
@Service
public class MappingServiceImpl implements MappingService {

    @Autowired
    private Mapper mapper;
    
    @Override
    public Mapper getMapper() {
        return mapper;
    }

    @Override
    public <T> T mapTo(Object objectToMap, Class<T> classToMapTo) {
        return mapper.map(objectToMap, classToMapTo);
    }
    
    @Override
    public <T> List<T> mapToCollection(Collection<?> objectsToMap, Class<T> classToMapTo) {
        List<T> mappedObjects = new ArrayList<>();
        for (Object object : objectsToMap)
        {
            mappedObjects.add(mapper.map(object, classToMapTo));
        }
        return mappedObjects;
    }
    
}
