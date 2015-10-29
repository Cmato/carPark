//package cz.muni.fi.pa165.carPark;
//
//import cz.muni.fi.pa165.carPark.daos.EmployeeDao;
//import cz.muni.fi.pa165.carPark.daos.EmployeeDaoImpl;
//import cz.muni.fi.pa165.carPark.entities.Employee;
//import java.util.Calendar;
//import java.util.Date;
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//import junit.framework.TestCase;
//import static junit.framework.TestCase.fail;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
///**
// *
// * @author xcmarko
// */
//public class EmployeeDaoImplTest extends TestCase {
//    
//    private EmployeeDao DAO;
//    private EntityManagerFactory emf;
//    private EntityManager em;
//    
//    /*@Before
//    @Override
//    public void setUp(){
//        emf = Persistence.createEntityManagerFactory("carPark");
//        DAO = new EmployeeDaoImpl(emf);
//    }
//
//    @After
//    public void close() {
//        emf.close();
//        em.close();
//    }*/
//    
//    private static Employee newEmployee(String name, Date date, String idCardNumber) {
//        Employee employee = new Employee();
//        /*employee.setName(name);
//        employee.setBirth(date);
//        employee.setIdCardNumber(idCardNumber);*/
//
//        return employee;
//    }
//    
//    @Test
//    public void testCreateEmployee(){
//        /*Calendar cal = Calendar.getInstance();
//        cal.set(Calendar.YEAR, 1988);
//        cal.set(Calendar.MONTH, Calendar.JANUARY);
//        cal.set(Calendar.DAY_OF_MONTH, 1);
//        Date dateRepresentation = cal.getTime();
//        Employee employee;
//        employee = newEmployee("Ignac", dateRepresentation, "AA123456");
//        DAO.createEmployee(employee);
//        
//        assertNotNull(employee.getId());
//        assertNotNull(employee.getName());
//        assertNotNull(employee.getAddress());
//        
//        Employee sameEmployee = DAO.findEmployeeById(employee.getId());
//        
//        assertEquals(employee, sameEmployee);
//        assertNotSame(employee, sameEmployee);*/
//    } 
//    
//    @Test
//    public void testCreateEmployeeWithNulls(){
//        /*Employee employee = newEmployee(null, null, null);
//        
//        try {
//            DAO.createEmployee(null);
//            fail("Ex not thrown");
//        } catch (IllegalArgumentException ex) {
//            
//        }
//        
//        try {
//            DAO.createEmployee(employee);
//            fail("Ex not thrown");
//        } catch (IllegalArgumentException ex) {
//            
//        }*/
//    } 
//    
//    @Test
//    public void testFindEmployeeById(){
//        /*Calendar cal = Calendar.getInstance();
//        cal.set(Calendar.YEAR, 1988);
//        cal.set(Calendar.MONTH, Calendar.JANUARY);
//        cal.set(Calendar.DAY_OF_MONTH, 1);
//        Date dateRepresentation = cal.getTime();
//        Employee employee1 = newEmployee("Ignac", dateRepresentation, "AA123456");
//        Employee employee2 = newEmployee("Dudko", dateRepresentation, "FF654321");
//
//        DAO.createEmployee(employee1);
//        DAO.createEmployee(employee2);
//        
//        assertEquals(DAO.findEmployeeById(employee1.getId()),employee1);
//        assertEquals(DAO.findEmployeeById(employee2.getId()),employee2);*/
//    }
//    
//    @Test 
//    public void testGetEmployeeWithNullId() {
//        /*try {
//            DAO.findEmployeeById(null);
//            fail("Ex not thrown");
//        } catch (IllegalArgumentException ex) {
//            
//        }*/
//    }
//    
//    @Test 
//    public void testDeleteEmployee() {
//        /*Calendar cal = Calendar.getInstance();
//        cal.set(Calendar.YEAR, 1988);
//        cal.set(Calendar.MONTH, Calendar.JANUARY);
//        cal.set(Calendar.DAY_OF_MONTH, 1);
//        Date dateRepresentation = cal.getTime();
//        Employee employee = newEmployee("Ignac", dateRepresentation, "AA123456");
//        DAO.createEmployee(employee);
//        assertNotNull(DAO.findEmployeeById(employee.getId()));
//        DAO.deleteEmployee(employee);
//        try {
//            DAO.deleteEmployee(employee);
//            fail("Ex not thrown");
//        } catch (IllegalArgumentException ex) {
//            
//        }*/
//    }
//    
//    @Test 
//    public void testDeleteWithNullId() {
//        /*try {
//            DAO.deleteEmployee(null);
//            fail("Ex not thrown");
//        } catch (IllegalArgumentException ex) {
//            
//        }*/
//    }
//    
//    @Test 
//    public void testUpdateEmployee() {
//        /*Calendar cal = Calendar.getInstance();
//        cal.set(Calendar.YEAR, 1988);
//        cal.set(Calendar.MONTH, Calendar.JANUARY);
//        cal.set(Calendar.DAY_OF_MONTH, 1);
//        Date dateRepresentation = cal.getTime();
//        Employee employee = newEmployee("Ignac", dateRepresentation, "AA123456");
//        DAO.createEmployee(employee);
//        
//        Employee employeeUpdated = DAO.findEmployeeById(employee.getId());
//        employeeUpdated.setName("Jozko");
//        
//        Calendar calUp = Calendar.getInstance();
//        calUp.set(Calendar.YEAR, 1990);
//        calUp.set(Calendar.MONTH, Calendar.JANUARY);
//        calUp.set(Calendar.DAY_OF_MONTH, 1);
//        Date updatedDate = calUp.getTime();
//        employeeUpdated.setBirth(updatedDate);
//        
//        employeeUpdated.setIdCardNumber("FF654321");
//        DAO.updateEmployee(employeeUpdated);
//        Employee verifyEmployee = DAO.findEmployeeById(employee.getId());
//        
//        if(verifyEmployee.getName() != "Jozko")
//            fail("Employee name was not updated");
//        
//        if(verifyEmployee.getBirth() != updatedDate)
//            fail("Employee birht was not updated");
//        
//        if(verifyEmployee.getIdCardNumber() != "FF654321")
//            fail("Employee id card number was not updated");
//        
//        assertEquals(employeeUpdated, verifyEmployee);
//        assertNotSame(employeeUpdated, verifyEmployee);*/
//    
//    }
//}
