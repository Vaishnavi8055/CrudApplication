package com.incs.crudapplication.Service;/*
 * Created by Vaishnavi Chaurasia
 * 03-Mar-22
 * 10:26 PM
 * crudapplication
 */

import com.incs.crudapplication.Common.Department;
import com.incs.crudapplication.Common.Employee;
import com.incs.crudapplication.Request.EmployeeRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

// crud operations / logic part
@Service
public class EmployeeService {

    private static final Logger LOG = LogManager.getLogger();

    @Autowired
    private SessionFactory sessionFactory;

    // pass user input / request in parameter


    /**
     * @param employeeRequest
     * @return
     */

    public boolean insertIntoEmployeeTable(EmployeeRequest employeeRequest) {
        Transaction transaction = null;
        Session session = this.sessionFactory.openSession();

        try {
            Employee employee = new Employee();
            employee.setEmpId(employeeRequest.getEmpId());
            employee.setAge(employeeRequest.getAge());
            employee.setFirstName(employeeRequest.getFirstName());
            employee.setLastName(employeeRequest.getLastName());
            employee.setCity(employeeRequest.getCity());
            employee.setSalary(employeeRequest.getSalary());

            Query query = session.createQuery("from Department where deptName =: deptName");
            query.setParameter("deptName", employeeRequest.getDepartmentName());
            // all records
            List<Department> departmentInfo = query.list();
            employee.setDepartment(departmentInfo.get(0));
            transaction = session.beginTransaction();
            session.save(employee);

            System.out.println("------------------------------------------");
            System.out.println("Inserted Employee - " + employee.getFirstName() + " " + employee.getLastName());
            System.out.println("------------------------------------------");

            //commit all changes during transaction
            transaction.commit();

        } catch (HibernateException e) {

            if (transaction != null)
                transaction.rollback();

            e.printStackTrace();

        } finally {
            try {
                if (session != null) session.close();
            } catch (Exception ex) {
            }
        }

        return true;
    }


    /**
     * @param empId
     * @return
     */

    public boolean retrieveEmployeeData(@PathVariable long empId) {

        Transaction transaction = null;
        Session session = this.sessionFactory.openSession();

        try {
            // get Entity/Class
            // second parameter is the primary key
            Employee employee = (Employee) session.get(Employee.class, empId);

            System.out.println("------------------------------------------------");

            System.out.println("Employee's ID :- " + employee.getEmpId());
            System.out.println("Employee's First Name :- " + employee.getFirstName());
            System.out.println("Employee's Last Name :- " + employee.getLastName());
            System.out.println("Employee's Age :- " + employee.getAge());
            System.out.println("Employee's salary :- " + employee.getSalary());
            System.out.println("Employee's City :- " + employee.getCity());
            System.out.println("Employee's DepartmentId :- " + employee.getDepartment().getDeptId());

            System.out.println("------------------------------------------------");


            //start the transaction/work
            transaction = session.beginTransaction();


            transaction.commit();

        } catch (HibernateException e) {

            if (transaction != null)
                transaction.rollback();

            e.printStackTrace();

        } finally {
            try {
                if (session != null) session.close();
            } catch (Exception ex) {
            }
        }
        return true;
    }


    /**
     * @param empId
     * @return
     */
    public boolean deleteEmployeeData(@PathVariable long empId) {

        Transaction transaction = null;
        Session session = this.sessionFactory.openSession();

        try {
            // get Entity/Class
            // second parameter is the primary key
            Employee employee = (Employee) session.get(Employee.class, empId);

            System.out.println("Data Deleting of : " + employee.getFirstName() + " " + employee.getLastName());

            //start the transaction/work
            transaction = session.beginTransaction();

            // update session
            session.delete(employee);

            transaction.commit();

        } catch (HibernateException e) {

            if (transaction != null)
                transaction.rollback();

            e.printStackTrace();

        } finally {
            try {
                if (session != null) session.close();
            } catch (Exception ex) {
            }
        }

        return true;
    }


    /**
     * @param empId
     * @return
     */
    public boolean updateEmployeeTable(@PathVariable long empId) {

        Transaction transaction = null;
        Session session = this.sessionFactory.openSession();

        try {
            // get Entity/Class
            // second parameter is the primary key
            Employee employee = (Employee) session.get(Employee.class, empId);
            System.out.println("Data Before Updating : " + employee.getLastName());
            // updated Last name of employee with id 12

            employee.setCity("Jaipur");

            //start the transaction/work
            transaction = session.beginTransaction();

            // update session
            session.update(employee);

            transaction.commit();
            System.out.println("Data After Updating : " + employee.getLastName());

        } catch (HibernateException e) {

            if (transaction != null)
                transaction.rollback();

            e.printStackTrace();

        } finally {
            try {
                if (session != null) session.close();
            } catch (Exception ex) {
            }
        }

        return true;
    }

    /**************************************************************** TASK-3 IN HQL **********************************************************/

    /*******************************    1. FIND/Get TOP 5 EMPLOYEE WTH HIGHEST SALARY        **************************************/


    public List<Employee> getTopFiveEmployeeWithMaxSalary() {

        Transaction transaction = null;
        Session session = this.sessionFactory.openSession();

        Query query = session.createQuery("Select empId,firstName,lastName,salary from Employee order by salary desc");
        query.setMaxResults(5);
        List<Employee> result = query.list();

        //start the transaction/work
        transaction = session.beginTransaction();

        transaction.commit();
        session.close();

        return result;
    }

    /*******************************    2. FIND/Get EMPLOYEE WTH HIGHEST SALARY        **************************************/


    public List<Employee> getEmployeeWithMaxSalary() {

        Transaction transaction = null;
        Session session = this.sessionFactory.openSession();

        Query query = session.createQuery("Select empId,firstName,lastName,salary from Employee order by salary desc");
        query.setMaxResults(1);
        List<Employee> result = query.list();

        //start the transaction/work
        transaction = session.beginTransaction();

        transaction.commit();
        session.close();

        return result;
    }


    /*******************************    3. FIND/Get EMPLOYEE WTH Min SALARY        **************************************/


    public List<Employee> getEmployeeWithMinSalary() {

        Transaction transaction = null;
        Session session = this.sessionFactory.openSession();

        Query query = session.createQuery("Select empId,firstName,lastName,salary from Employee order by salary asc");
        query.setMaxResults(1);
        List<Employee> result = query.list();

        //start the transaction/work
        transaction = session.beginTransaction();

        transaction.commit();
        session.close();

        return result;
    }


    /*******************************    4. FIND/Get Total salary paid by department       **************************************/


    public List<Employee> getTotalSalaryPaid() {

        Transaction transaction = null;
        Session session = this.sessionFactory.openSession();

        Query query = session.createQuery("Select d.deptName , Sum(e.salary) From Employee e Inner Join e.department d group by d.deptName");
        List<Employee> result = query.list();

        //start the transaction/work
        transaction = session.beginTransaction();

        transaction.commit();
        session.close();

        return result;
    }


    /*******************************    5. FIND/Get Avg salary paid department wise      **************************************/


    public List<Employee> getAvgSalaryPaidDeptWise() {

        Transaction transaction = null;
        Session session = this.sessionFactory.openSession();

        Query query = session.createQuery("Select d.deptId, d.deptName , avg(e.salary) From Employee e Inner Join e.department d group by d.deptName");
        List<Employee> result = query.list();

        //start the transaction/work
        transaction = session.beginTransaction();

        transaction.commit();
        session.close();

        return result;
    }


    /*******************************    6. FIND/Get Employee Salary Greater than Average  **************************************/


    public List<Employee> getAvgSalaryGreaterThanAvg() {

        Transaction transaction = null;
        Session session = this.sessionFactory.openSession();

        Query query = session.createQuery("Select e.empId,e.firstName,e.lastName,e.city,e.salary FROM Employee e where e.salary > (Select avg(e.salary) from Employee) ");
        List<Employee> result = query.list();

        //start the transaction/work
        transaction = session.beginTransaction();

        transaction.commit();
        session.close();

        return result;
    }

    /*******************************    7. FIND/Get Employee By City  **************************************/

    // ERROR
    public List<Employee> getEmployeeByCity(String city) {

        Transaction transaction = null;
        Session session = this.sessionFactory.openSession();

        Query query = session.createQuery("Select e.empId,e.firstName,e.lastName,e.city,e.salary FROM Employee e where city =: city");
        query.setParameter("city", city);
        List<Employee> result = query.list();

        //start the transaction/work
        transaction = session.beginTransaction();

        transaction.commit();
        session.close();

        return result;
    }

    /*******************************    8. Display all details of Employee with dept Name  **************************************/


    public List<Employee> getEmployeeDetailWithDeptName() {

        Transaction transaction = null;
        Session session = this.sessionFactory.openSession();

        Query query = session.createQuery("Select e.empId, e.firstName,e.lastName,e.city,e.salary, d.deptName FROM Employee e inner join e.department d");
        List<Employee> result = query.list();

        //start the transaction/work
        transaction = session.beginTransaction();

        transaction.commit();
        session.close();

        return result;
    }


    /*******************************    9. Display all employee in sorting order using salary  **************************************/


    public List<Employee> sortSalaryInAsc() {

        Transaction transaction = null;
        Session session = this.sessionFactory.openSession();

        Query query = session.createQuery("Select e.empId, e.firstName,e.lastName,e.city,e.salary FROM Employee e order by e.salary asc");
        List<Employee> result = query.list();

        //start the transaction/work
        transaction = session.beginTransaction();

        transaction.commit();
        session.close();

        return result;
    }


    /*******************************    10. Display all employee in sorting order using salary  **************************************/


    public List<Employee> sortSalaryInDesc() {

        Transaction transaction = null;
        Session session = this.sessionFactory.openSession();

        Query query = session.createQuery("Select e.empId, e.firstName,e.lastName,e.city,e.salary FROM Employee e order by e.salary desc ");
        List<Employee> result = query.list();

        //start the transaction/work
        transaction = session.beginTransaction();

        transaction.commit();
        session.close();

        return result;
    }


    /*******************************    11. Display employee detail having age greater than average age   **************************************/


    public List<Employee> getEmpWithAgeGreaterThanAvgAge() {

        Transaction transaction = null;
        Session session = this.sessionFactory.openSession();

        Query query = session.createQuery("Select e.empId, e.firstName,e.lastName,e.city,e.salary From Employee e where e.age > (SELECT avg(age) from Employee )");
        List<Employee> result = query.list();

        //start the transaction/work
        transaction = session.beginTransaction();

        transaction.commit();
        session.close();

        return result;
    }


}


