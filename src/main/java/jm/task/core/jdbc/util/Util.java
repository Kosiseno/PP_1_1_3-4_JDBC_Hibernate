package jm.task.core.jdbc.util;

import com.mysql.cj.jdbc.MysqlDataSource;
import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.registry.internal.StandardServiceRegistryImpl;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {

    private static SessionFactory sessionFactory = getSessionFactory();

    public static SessionFactory getSessionFactory(){
        Configuration configuration = new Configuration();
        try(InputStream in = Files.newInputStream((Paths.get("src/main/java/jm/task/core/jdbc/resources/hibernate.properties")))) {
            Properties properties = new Properties();
            properties.load(in);
            configuration.setProperties(properties);
            configuration.addAnnotatedClass(User.class);
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }

    public static SessionFactory buildSessionFactory() {
        try {
            File file = new File("src/main/java/jm/task/core/jdbc/resources/hibernate.properties");
            StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .loadProperties(file).build();
            Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
            return metadata.getSessionFactoryBuilder().build();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }



        public static Connection getMySQLConnection() {
        Connection connect = null;
        Properties properties = new Properties();
        try(InputStream in = Files.newInputStream(Paths.get("src/main/java/jm/task/core/jdbc/resources/db.properties"))) {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return connect = DriverManager.getConnection(properties.getProperty("url"),
                    properties.getProperty("username"),properties.getProperty("password"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connect;
    }



    }





