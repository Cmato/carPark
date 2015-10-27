/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.carPark.daos;

import cz.muni.fi.pa165.carPark.entities.Employee;
import java.util.List;

/**
 *
 * @author xcmarko
 */
public interface EmployeeDao {
    void createCar(Employee employee);
    void deleteCar(Employee employee);
    void updateCar(Employee employee);
    Employee findCarById(Long id);
    List<Employee> findAllCars();
}
