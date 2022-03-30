package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private static Session session = null;

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        session = Util.getSessionFactory().openSession();
        session.getTransaction().begin();
        String SQL = "CREATE TABLE IF NOT EXISTS users ( id BIGINT auto_increment, constraint users_pk primary key (id), name VARCHAR(255), lastname VARCHAR(255), age TINYINT);";
        session.createSQLQuery(SQL).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        session = Util.getSessionFactory().openSession();
            session.getTransaction().begin();
            String SQL = "DROP TABLE IF EXISTS users;";
            session.createSQLQuery(SQL).executeUpdate();
            session.getTransaction().commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User();
        user.setName(name);
        user.setLastName(lastName);
        user.setAge(age);
        session = Util.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.save(user);
        session.getTransaction().commit();
        session.close();


    }

    @Override
    public void removeUserById(long id) {
        session = Util.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.createQuery("DELETE FROM " + User.class.getSimpleName() +" WHERE id = :id").setParameter("id",id).executeUpdate();
        session.getTransaction().commit();
        session.close();


    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        session = Util.getSessionFactory().openSession();
        session.getTransaction().begin();
        String SQL ="From " + User.class.getSimpleName() ;
        userList = session.createQuery(SQL).list();
        session.getTransaction().commit();
        session.close();
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        session = Util.getSessionFactory().openSession();
        session.getTransaction().begin();
        String SQL = "DELETE FROM users;";
        session.createSQLQuery(SQL).executeUpdate();
        session.getTransaction().commit();
        session.close();

    }
}
