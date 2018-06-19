package Datenbank;

import java.sql.*;


public class JDBCClient {


        JDBCClient meinJDBC = new JDBCClient();

    public Connection connect() {

        Connection conn;

        String host = "jdbc:mysql://127.0.0.1:3306/";
        String dbName = "SWT";
        String username = "SWT";
        String password = "SWT";

        String url = host + dbName
                + "?user=" + username
                + "&password=" + password;


        try {
            String treiber = "com.mysql.jdbc.Driver";
            Class.forName(treiber);
            System.out.println("Info: Treiber " + treiber + "[ok]");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }


        try {
            System.out.print("Info: Verbindung zu " + url);
            conn = DriverManager.getConnection(url);
            System.out.println("[ok]");
        } catch (SQLException e) {
            // TODO Auto-generated catch block

            e.printStackTrace();
            return null;
        }

        return conn;
    }

    public void tuwas(Connection conn) throws Throwable // erstellt ein Statement
    {
        Statement stat = null;

        try {
            stat = conn.createStatement();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }


        try {
            stat.execute("DROP TABLE IF EXISTS test");
            System.out.println("Info: Tabelle gelöscht - [ok]");
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

    }


    public Connection disconnect(Connection conn) {
        try {
            conn.close();
            conn = null;
            System.out.println("Info: Connection geschlossen" + "[ok]");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}

