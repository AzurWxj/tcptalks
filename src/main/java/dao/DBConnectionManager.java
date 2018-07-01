package dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public class DBConnectionManager {

    private static final String
            JDBC_DRIVER = "com.mysql.jdbc.Driver",
            DB_URL = "jdbc:mysql://localhost:3306/tcptalks?serverTimezone=UTC&useUnicode=yes&characterEncoding=UTF-8",
            DB_USER="root",
            DB_PASSWORD="948337182,Brian";

    private static Logger logger=Logger.getLogger(DBConnectionManager.class);

    private static DBConnectionManager connectionManager = new DBConnectionManager();

    private static ComboPooledDataSource dataSource;

    public static DBConnectionManager getInstance() {
        return connectionManager;
    }

    private DBConnectionManager() {
        logger.setLevel(Level.ALL);
        dataSource=new ComboPooledDataSource();
        logger.info("创建数据库连接池");
        try {
            dataSource.setDriverClass(JDBC_DRIVER);
            logger.info("设置MySQL驱动："+JDBC_DRIVER);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        dataSource.setUser(DB_USER);
        logger.info("设置用户名："+DB_USER);
        dataSource.setPassword(DB_PASSWORD);
        logger.info("设置密码："+DB_PASSWORD);
        dataSource.setJdbcUrl(DB_URL);
        logger.info("设置数据库URL："+DB_URL);
        dataSource.setInitialPoolSize(3);
        logger.info("设置初始连接对象个数：3");
        dataSource.setMaxPoolSize(20);
        logger.info("设置最大连接对象个数：20");
        dataSource.setMinPoolSize(3);
        logger.info("设置最小连接对象数量：3");
        dataSource.setMaxStatements(20);
        logger.info("设置最大PreparedStatement缓存数量：20");
        dataSource.setMaxIdleTime(0);
        logger.info("设置最大空闲时间：0（即永远生效）");
        logger.info("DBConnectionManager单例对象创建完成");
    }

    public final synchronized Connection getConnection(){
        Connection connection=null;
        try {
            connection=dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        logger.info("返回连接对象");
        return connection;
    }

    public static void main(String[] args){
        //PropertyConfigurator.configure("log4j.properties");
        logger.info("创建数据库连接池");
        try(Connection connection=DBConnectionManager.getInstance().getConnection()){

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
