package Datenbank;

import java.sql.*;


public class ConnectionManager {

    private Connection connection;
    static boolean created = false;

    // Singleton Entwurfsmuster
    public ConnectionManager() {

    }

    public static ConnectionManager create()
    {
        if(!created)
        {
		    ConnectionManager db = new ConnectionManager();
			db.connectDB("db4free.net", "swttest", "swttest", "swtadmin");
            created = true;
            System.out.println("Eine DB-Verbindung wurde erstellt.");
            return new ConnectionManager();
        } else {
            System.out.println("Es besteht bereits eine DB-Verbindung.");
            return null;
        }
    }

    public Connection connectDB(String host, String database, String user, String passwd) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            String connectionCommand = "jdbc:mysql://" + host + "/" + database + "?user=" + user + "&password="
                    + passwd;
            connection = DriverManager.getConnection(connectionCommand);
            System.out.println("Erfolgreich verbunden.");
            // return true;
            return connection;

        } catch (Exception ex) {
            System.out.println("false - Nicht verbunden.");
            System.out.println(ex);
            // return false;
            return connection;
        }
    }


    public Connection close()
    {
        try
        {
            connection.close();
            connection = null;
            created = false;
            System.out.println("Die Verbindung zur Datenbank wurde geschlossen.");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return connection;
    }
}


