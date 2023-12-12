package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

    private final SessionFactory sessionFactory;
    private Employee employee;

    public EmployeeDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void createEmployee(Employee employee) {
        this.employee = employee;
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void createEmployee() {
        createEmployee(null);
    }

    @Override
    public void createEmployee(Employee employee) {

    }

    @Override
    public Employee getEmployeeById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Employee.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        try (Session session = sessionFactory.openSession()) {
            List<Employee> from_employee = session.createQuery("FROM Employee", Employee.class).list();
            return from_employee;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void updateEmployee(Employee employee) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(employee);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void deleteEmployee(int id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Employee employee = session.get(Employee.class, id);
            if (employee != null) {
                session.delete(employee);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
