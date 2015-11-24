package cz.muni.fi.pa165.carPark;

import cz.muni.fi.pa165.daos.EmployeeDao;
import cz.muni.fi.pa165.daos.EmployeeDaoImpl;
import cz.muni.fi.pa165.entities.Employee;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class App 
{
    private static EntityManagerFactory emf;
    
    //@Autowired
    //private EmployeeDao ed;// = new EmployeeDaoImpl();
    
    public static void main( String[] args )
    {

        new AnnotationConfigApplicationContext(InMemoryDatabaseSpring.class);

	emf = Persistence.createEntityManagerFactory("carPark");
        
        
        /*EmployeeDao ed = new EmployeeDaoImpl();
        
        Employee empl = new Employee();
        empl.setName("John Snow");
        Calendar cal1 = Calendar.getInstance();
	cal1.set(Calendar.YEAR, 2011);
	cal1.set(Calendar.MONTH, 0);
	cal1.set(Calendar.DAY_OF_MONTH, 20);
        empl.setBirth(cal1.getTime());
        System.out.println(empl.getName() + "  " + empl.getBirth());
        ed.createEmployee(empl);
	//prod.setAddedDate(cal1.getTime());
            EntityManager emWork = emf.createEntityManager();
		emWork.getTransaction().begin();
		Category c1 = new Category();
		c1.setName("Electronics");
		Category c2=new Category();
		c2.setName("Musical");
		emWork.persist(c1);
		emWork.persist(c2);
		
		emWork.getTransaction().commit();*/
        
        /*Calendar cal2 = Calendar.getInstance();
        //cal2.getTime();
        Date date = cal2.getTime();
        System.out.println("Actual time: " + date);*/
	emf.close();
        
        
    }
}
