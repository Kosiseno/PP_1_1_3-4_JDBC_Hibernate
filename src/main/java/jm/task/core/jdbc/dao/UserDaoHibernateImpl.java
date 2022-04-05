package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    /*
    Использовал User.class.getSimpleName() потому что idea ругалась, когда писал просто User.
    Умом понимаю, что это одно и то же. После перезагрузки, ругаться перестал. ¯\_(ツ)_/¯
     */

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            try {
                session.beginTransaction();
                String SQL = "CREATE TABLE IF NOT EXISTS users ( id BIGINT auto_increment, constraint users_pk primary key (id), name VARCHAR(255), lastname VARCHAR(255), age TINYINT);";
                session.createSQLQuery(SQL).executeUpdate();
                session.getTransaction().commit();
            } catch (HibernateException e) {
            session.getTransaction().rollback();
        }

        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            try {
                session.beginTransaction();
                String SQL = "DROP TABLE IF EXISTS users;";
                session.createSQLQuery(SQL).executeUpdate();
                session.getTransaction().commit();
            } catch (HibernateException e) {
                session.getTransaction().rollback();
            }

        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User();
        user.setName(name);
        user.setLastName(lastName);
        user.setAge(age);
        try (Session session = Util.getSessionFactory().openSession()) {
            try{
                session.beginTransaction();
                session.save(user);
                session.getTransaction().commit();
                System.out.println("User с именем – " +name + " добавлен в базу данных");
            } catch (HibernateException e) {
                session.getTransaction().rollback();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()) {
            try {
                session.beginTransaction();
                session.createQuery("DELETE FROM User WHERE id = :id").setParameter("id", id).executeUpdate();
                session.getTransaction().commit();
            } catch (HibernateException e) {
                session.getTransaction().rollback();
            }


        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Session session = Util.getSessionFactory().openSession()) {
            try {
                session.beginTransaction();
                String SQL = "From User";
                userList = session.createQuery(SQL).list();
                session.getTransaction().commit();
            } catch (HibernateException e) {
                session.getTransaction().rollback();
            }


        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            try {
                session.beginTransaction();
                String SQL = "DELETE FROM users;";
                session.createSQLQuery(SQL).executeUpdate();
                session.getTransaction().commit();
            } catch (HibernateException e) {
                session.getTransaction().rollback();
            }


        }

    }
}
