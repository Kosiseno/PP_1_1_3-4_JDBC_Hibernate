package jm.task.core.jdbc.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {

    public static Connection getMySQLConnection(){
        Connection connect = null;
        Properties properties = new Properties();
        try(InputStream in = Files.newInputStream(Paths.get("src/main/java/jm/task/core/jdbc/resources/db.properties"))) {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            connect = DriverManager.getConnection(properties.getProperty("url"),
                    properties.getProperty("username"),properties.getProperty("password"));
            connect.setAutoCommit(false);
            return connect;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connect;
    }


}
