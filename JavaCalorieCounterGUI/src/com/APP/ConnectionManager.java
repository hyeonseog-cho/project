package com.APP;

import java.io.IOException;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import oracle.jdbc.OracleConnection;
import oracle.jdbc.pool.OracleDataSource;

public class ConnectionManager {
    final static String DB_URL= "jdbc:oracle:thin:@l2hxkcko1rce3vyf_tp?TNS_ADMIN=";
    final static String WALLET_NAME = "Wallet_L2HXKCKO1RCE3VYF";
    final static String DB_USER = "CALORIEAPP";
    final static String DB_PASSWORD = "rerWQ8S8bAgQ";

    static OracleDataSource dataSource;

    public ConnectionManager() throws SQLException, IOException {
        if (dataSource == null) {
            Path walletPath = Path.of("").toAbsolutePath();
            walletPath = walletPath.normalize();
            walletPath = walletPath.resolve(WALLET_NAME);

            String walletPathString = walletPath.toString();

            // Check if windows
            String operSys = System.getProperty("os.name").toLowerCase();
            if (operSys.contains("win")) {
                walletPathString = walletPathString.replace("\\", "\\\\");
            }
            
            String finalPath = DB_URL + walletPathString;
            // System.out.println(finalPath);

            Properties info = new Properties();
            info.put(OracleConnection.CONNECTION_PROPERTY_USER_NAME, DB_USER);
            info.put(OracleConnection.CONNECTION_PROPERTY_PASSWORD, DB_PASSWORD);          
            info.put(OracleConnection.CONNECTION_PROPERTY_DEFAULT_ROW_PREFETCH, "20");
        
            dataSource = new OracleDataSource();
            dataSource.setURL(finalPath);    
            dataSource.setConnectionProperties(info);
        }
    }

    public Connection get() throws SQLException {
        OracleConnection connection = (OracleConnection) dataSource.getConnection();
        return (Connection) connection;
    }
}