package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private static Connection connection = Util.getMySQLConnection();


    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            Statement statement = connection.createStatement();
            String SQL = "CREATE TABLE IF NOT EXISTS users ( id BIGINT auto_increment, constraint primary key (id), name VARCHAR(255), lastname VARCHAR(255), age TINYINT);";
            statement.executeUpdate(SQL);
            connection.commit();
        } catch (SQLException throwables) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void dropUsersTable() {
        try {
            Statement statement = connection.createStatement();
            String SQL = "DROP TABLE IF EXISTS users;";
            statement.executeUpdate(SQL);
            connection.commit();
        } catch (SQLException throwables) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (name, lastname, age) VALUES (?,?,?)");

            preparedStatement.setString(1,name);
            preparedStatement.setString(2,lastName);
            preparedStatement.setByte(3,age);

            preparedStatement.executeUpdate();
            connection.commit();
            System.out.println("User с именем – " +name + " добавлен в базу данных");
        } catch (SQLException throwables) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void removeUserById(long id) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id = ?");
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException throwables) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
                connection.commit();
            }

        } catch (SQLException throwables) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userList;
    }

    public void cleanUsersTable() {
        try {
            Statement statement = connection.createStatement();
            String SQL = "DELETE FROM users;";
            statement.executeUpdate(SQL);
            connection.commit();
        } catch (SQLException throwables) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
