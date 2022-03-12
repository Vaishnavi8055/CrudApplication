package com.incs.crudapplication.Service;/*
 * Created by Vaishnavi Chaurasia
 * 03-Mar-22
 * 10:29 PM
 * crudapplication
 */

import com.incs.crudapplication.Common.Department;
import com.incs.crudapplication.Request.DepartmentRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {


    private static final Logger LOG = LogManager.getLogger();

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * @param departmentRequest
     * @return
     */
    public boolean insertIntoDepartmentTable(DepartmentRequest departmentRequest) {

        Transaction transaction = null;
        Session session = this.sessionFactory.openSession();


        try {
            Department department = new Department();
            department.setDeptId(departmentRequest.getDeptId());
            department.setDeptCity(departmentRequest.getDeptCity());
            department.setDeptName(departmentRequest.getDeptName());


            //Hibernate session to save in database

            //start the transaction/work
            transaction = session.beginTransaction();


            // save the Employee object
            session.save(department);

            System.out.println("------------------------------------------");
            System.out.println("Inserted Department - " + department.getDeptName());
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
     * @param deptId
     * @return
     */

    public boolean retrieveDepartmentData(int deptId) {

        Transaction transaction = null;
        Session session = this.sessionFactory.openSession();


        try {
            // get Entity/Class
            // second parameter is the primary key
            Department department = (Department) session.get(Department.class, deptId);

            System.out.println("------------------------------------------------");

            System.out.println("Department ID :- " + department.getDeptId());
            System.out.println("Department Name :- " + department.getDeptName());
            System.out.println("Department City :- " + department.getDeptCity());

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
}