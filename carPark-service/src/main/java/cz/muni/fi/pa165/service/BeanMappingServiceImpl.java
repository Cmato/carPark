package cz.muni.fi.pa165.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author xhubeny2
 */
public class BeanMappingServiceImpl implements BeanMappingService {

    @Autowired
    private Mapper dozer;

    public <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass) {
        List<T> mappedCollection = new ArrayList<T>();
        for (Object object : objects) {
            mappedCollection.add(dozer.map(object, mapToClass));
        }
        return mappedCollection;
    }

    public <T> T mapTo(Object obj, Class<T> mapToClass) {
        return dozer.map(obj, mapToClass);
    }

    public Mapper getMapper() {
        return dozer;
    }

}
