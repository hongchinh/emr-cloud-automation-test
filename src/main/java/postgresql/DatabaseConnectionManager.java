package postgresql;


import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import config.ReadDBConfigFile;
import utils.LogHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnectionManager {
    private static ThreadLocal<Connection> connection = new ThreadLocal<>();

    private static void setConnection(Connection c){
        connection.set(c);
    }
    public static Connection getConnection(){
        return connection.get();
    }

    public static void startConnectionNoSSH(){
        ReadDBConfigFile configFile = new ReadDBConfigFile();
        String remoteHost = configFile.getProperty("remote.host");
        String dbName = configFile.getProperty("db.name");
        String userName = configFile.getProperty("user.name");
        String password = configFile.getProperty("user.password");
        int remotePort = configFile.getIntegerProperty("remote.port");;

        //POSTGRESQL
        String connectionUrl = "jdbc:postgresql://" + remoteHost + ":" + remotePort + "/" + dbName;
        Connection connection = null;

        try{
            connection = DriverManager.getConnection(connectionUrl, userName, password);
            LogHelper.getInstance().info("Connect to Postgresql successfully");
        }
        catch (SQLException e){
            LogHelper.getInstance().info("Failed to connect to Postgresql");
            LogHelper.getInstance().info(e.getMessage());
        }

        setConnection(connection);

    }

    public static void startConnection() throws JSchException {
        ReadDBConfigFile configFile = new ReadDBConfigFile();
        String user = configFile.getProperty("user");
        int port = configFile.getIntegerProperty("ssh.port");
        int localPort = configFile.getIntegerProperty("local.port");
        String host = configFile.getProperty("ssh.host");
        int remotePort = configFile.getIntegerProperty("remote.port");;
        String localHost = configFile.getProperty("local.host");
        String remoteHost = configFile.getProperty("remote.host");
        String dbName = configFile.getProperty("db.name");
        String userName = configFile.getProperty("user.name");
        String password = configFile.getProperty("user.password");
        //SSH
        JSch jsch =  new JSch();
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        jsch.addIdentity(System.getProperty("user.dir") + "/src/test/resources/abc.cer");
        Session session = jsch.getSession(user, host, port);
        session.setConfig(config);
        session.connect();
        int assignedPort = session.setPortForwardingL(0, remoteHost, remotePort);

        LogHelper.getInstance().info("Assigned port: " + assignedPort);

        //POSTGRESQL
        String connectionUrl = "jdbc:postgresql://" + localHost + ":" + assignedPort + "/" + dbName;
        Connection connection = null;

        try{
            connection = DriverManager.getConnection(connectionUrl, userName, password);
            LogHelper.getInstance().info("Connect to Postgresql successfully");
        }
        catch (SQLException e){
            LogHelper.getInstance().info("Failed to connect to Postgresql");
            LogHelper.getInstance().info(e.getMessage());
        }

        setConnection(connection);
    }
    public static void closeConnection() throws SQLException {
        if(getConnection().isValid(5)){
            try{
                getConnection().close();
                LogHelper.getInstance().info("Close Postgresql successfully");
            }
            catch (SQLException e){
                LogHelper.getInstance().info("Failed to close Postgresql");
                LogHelper.getInstance().info(e.getMessage());
            }
        };
    }

}
