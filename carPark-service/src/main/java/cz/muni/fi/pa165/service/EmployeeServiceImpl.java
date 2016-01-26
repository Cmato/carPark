package cz.muni.fi.pa165.service;

import java.util.List;
import org.springframework.stereotype.Service;
import cz.muni.fi.pa165.daos.EmployeeDao;
import cz.muni.fi.pa165.entities.Employee;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.inject.Inject;

/**
 *
 * @author xhasprun
 */

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Inject
    private EmployeeDao employeeDao;
    
    @Override
    public Employee createEmployee(Employee employee) {
        employee.setPassword(createHash(employee.getPassword()));
        if(employeeDao.createEmployee(employee)) {
            return employee;
        }
        return null;
    }

    @Override
    public boolean deleteEmployee(Employee employee) {
        return employeeDao.deleteEmployee(employee);
    }

    @Override
    public Employee updateEmployeeName(Employee employee, String newName) {
        if(employee != null && newName != null && !newName.isEmpty()) {
            employee.setName(newName);
            return employeeDao.updateEmployee(employee);
        }
        return null;
    }
    
    @Override
    public Employee updateEmployeeEmail(Employee employee, String newEmail) {
        if(employee != null && newEmail != null && !newEmail.isEmpty()) {
            employee.setEmail(newEmail);
            return employeeDao.updateEmployee(employee);
        }
        return null;
    }
    
    @Override
    public Employee updateEmployeePassword(Employee employee, String newPassword) {
        if(employee != null && newPassword != null && !newPassword.isEmpty()) {
            employee.setPassword(createHash(newPassword));
            return employeeDao.updateEmployee(employee);
        }
        return null;
    }

    @Override
    public Employee updateEmployeeBirth(Employee employee, Date newBirth) {
        if(employee != null && newBirth != null) {
            employee.setBirth(newBirth);
            return employeeDao.updateEmployee(employee);
        }
        return null;
    }

    @Override
    public Employee updateEmployeeIdCardNumber(Employee employee, String newIdCarNumber) {
        if(employee != null && newIdCarNumber != null && !newIdCarNumber.isEmpty()) {
            employee.setIdCardNumber(newIdCarNumber);
            return employeeDao.updateEmployee(employee);
        }
        return null;
    }

    @Override
    public Employee findEmployeeById(Long id) {
        return employeeDao.findEmployeeById(id);
    }

    @Override
    public List<Employee> findAllEmployees() {
        return employeeDao.findAllEmployees();
    }
    
    @Override
    public List<Employee> findEmployeesInBirthRange(Date from, Date to) {
        List<Employee> result = new ArrayList<>();
        List<Employee> allEmployees = this.findAllEmployees();

        if(allEmployees.isEmpty()) {
            return result;
        }

        for(Employee item : allEmployees) {
            Date itemDate = item.getBirth();
            if(itemDate.after(from) && itemDate.before(to)) {
                result.add(item);
            }
        }
        return result;
    }

    @Override
    public Employee findEmployeeByEmail(String email) {
        return employeeDao.findEmployeeByEmail(email);
    }

    @Override
    public boolean authenticate(Employee employee, String password) {
        return validatePassword(password, employee.getPassword());
    }

    @Override
    public boolean isAdmin(Employee employee) {
        return findEmployeeById(employee.getId()).getIsAdmin();
    }

    //see  https://crackstation.net/hashing-security.htm#javasourcecode
    private static String createHash(String password) {
        final int SALT_BYTE_SIZE = 24;
        final int HASH_BYTE_SIZE = 24;
        final int PBKDF2_ITERATIONS = 1000;
        // Generate a random salt
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_BYTE_SIZE];
        random.nextBytes(salt);
        // Hash the password
        byte[] hash = pbkdf2(password.toCharArray(), salt, PBKDF2_ITERATIONS, HASH_BYTE_SIZE);
        // format iterations:salt:hash
        return PBKDF2_ITERATIONS + ":" + toHex(salt) + ":" + toHex(hash);
    }

    private static byte[] pbkdf2(char[] password, byte[] salt, int iterations, int bytes) {
        try {
            PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, bytes * 8);
            return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(spec).getEncoded();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean validatePassword(String password, String correctHash) {
        if(password==null) return false;
        if(correctHash==null) throw new IllegalArgumentException("password hash is null");
        String[] params = correctHash.split(":");
        int iterations = Integer.parseInt(params[0]);
        byte[] salt = fromHex(params[1]);
        byte[] hash = fromHex(params[2]);
        byte[] testHash = pbkdf2(password.toCharArray(), salt, iterations, hash.length);
        return slowEquals(hash, testHash);
    }

    /**
     * Compares two byte arrays in length-constant time. This comparison method
     * is used so that password hashes cannot be extracted from an on-line
     * system using a timing attack and then attacked off-line.
     *
     * @param a the first byte array
     * @param b the second byte array
     * @return true if both byte arrays are the same, false if not
     */
    private static boolean slowEquals(byte[] a, byte[] b) {
        int diff = a.length ^ b.length;
        for (int i = 0; i < a.length && i < b.length; i++)
            diff |= a[i] ^ b[i];
        return diff == 0;
    }

    private static byte[] fromHex(String hex) {
        byte[] binary = new byte[hex.length() / 2];
        for (int i = 0; i < binary.length; i++) {
            binary[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return binary;
    }

    private static String toHex(byte[] array) {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        return paddingLength > 0 ? String.format("%0" + paddingLength + "d", 0) + hex : hex;
    }
    
}
