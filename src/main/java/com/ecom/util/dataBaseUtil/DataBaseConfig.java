package com.ecom.util.dataBaseUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by a7289 on 2016/11/9 0009.
 */
public class DataBaseConfig {

//    private DataBaseConfig() {
//    }
//    public static String databaseDriver ;
//    public static String account;
//    public static String password;
//    public static String ipAddress;
//    public static String port;
//    public static String database;
////    private static Properties props = new Properties();
//
//    static {
//        try {
//            Properties props = new Properties();
//            // 将配置文件进行加载
//            InputStream in = DataBaseConfig.class.getResourceAsStream("/properties/dbconfig.properties");
//            props.load(in);
//            databaseDriver = props.getProperty("DATABASE_DRIVER");
//            account = props.getProperty("ACCOUNT");
//            password = props.getProperty("PASSWORD");
//            ipAddress = props.getProperty("IP_ADDRESS");
//            port = props.getProperty("PORT");
//            database = props.getProperty("DATABASE");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static String getDatabaseDriver() {
//        return databaseDriver;
//    }
//
//    public static void setDatabaseDriver(String databaseDriver) {
//        DataBaseConfig.databaseDriver = databaseDriver;
//    }
//
//    public static String getAccount() {
//        return account;
//    }
//
//    public static void setAccount(String account) {
//        DataBaseConfig.account = account;
//    }
//
//    public static String getPassword() {
//        return password;
//    }
//
//    public static void setPassword(String password) {
//        DataBaseConfig.password = password;
//    }
//
//    public static String getIpAddress() {
//        return ipAddress;
//    }
//
//    public static void setIpAddress(String ipAddress) {
//        DataBaseConfig.ipAddress = ipAddress;
//    }
//
//    public static String getPort() {
//        return port;
//    }
//
//    public static void setPort(String port) {
//        DataBaseConfig.port = port;
//    }
//
//    public static String getDatabase() {
//        return database;
//    }
//
//    public static void setDatabase(String database) {
//        DataBaseConfig.database = database;
//    }


    public static final String DATABASE_DRIVER;
    public static final String ACCOUNT;
    public static final String PASSWORD;
    public static final String IP_ADDRESS;
    public static final String PORT;
    public static final String DATABASE;

    static {
        Properties props = new Properties();
        try {
            // 将配置文件进行加载
            InputStream in = DataBaseConfig.class.getResourceAsStream("/properties/dbconfig.properties");
            props.load(in);

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            DATABASE_DRIVER = props.getProperty("DATABASE_DRIVER");
            ACCOUNT = props.getProperty("ACCOUNT");
            PASSWORD = props.getProperty("PASSWORD");
            IP_ADDRESS = props.getProperty("IP_ADDRESS");
            PORT = props.getProperty("PORT");
            DATABASE = props.getProperty("DATABASE");
        }
    }

}
