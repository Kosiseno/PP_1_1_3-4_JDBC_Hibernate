package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import javax.security.sasl.SaslServer;
import java.util.List;

public class Main {
    public static void main(String[] args) {
/*        Util.getMySQLConnection();
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Name1", "LastName1", (byte) 20);
        userService.saveUser("Name2", "LastName2", (byte) 25);
        userService.saveUser("Name3", "LastName3", (byte) 31);
        userService.saveUser("Name4", "LastName4", (byte) 38);

        userService.removeUserById(1);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();*/


        UserService userService = new UserServiceImpl();

        userService.dropUsersTable();
        userService.dropUsersTable();
        userService.createUsersTable();
        userService.saveUser("vasya1", "pupkin1",(byte) 23);
        userService.saveUser("vasya2", "pupkin2",(byte) 23);
        userService.saveUser("vasya3", "pupkin3",(byte) 23);
        userService.removeUserById(1);
        System.out.println(userService.getAllUsers().toString());
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
