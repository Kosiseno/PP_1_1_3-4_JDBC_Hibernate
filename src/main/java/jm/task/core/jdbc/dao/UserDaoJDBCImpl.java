package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private static Connection connection = Util.getMySQLConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            Statement statement = connection.createStatement();
            String SQL = "CREATE TABLE users ( id LONG, name VARCHAR(255), lastname VARCHAR(255), age TINYINT);";
            statement.executeUpdate(SQL);
        } catch (SQLException throwables) {
        }
    }

    public void dropUsersTable() {
        try {
            Statement statement = connection.createStatement();
            String SQL = "DROP TABLE users;";
            statement.executeUpdate(SQL);
        } catch (SQLException throwables) {
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        try {
            Statement statement = connection.createStatement();
            String SQL = "INSERT users(name, lastname, age) values ('"+ name +"', '" + lastName + "'," + age + ")";
            statement.executeUpdate(SQL);
            System.out.println("User с именем – " +name + " добавлен в базу данных");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        try {
            Statement statement = connection.createStatement();
            String SQL = "DELETE FROM users WHERE id =" + id +";";
            statement.executeUpdate(SQL);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM users;";
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));

                userList.add(user);
                System.out.println(user);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        try {
            Statement statement = connection.createStatement();
            String SQL = "DELETE FROM users;";
            statement.executeUpdate(SQL);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
