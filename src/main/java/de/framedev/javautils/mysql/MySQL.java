package de.framedev.javautils.mysql;

import java.sql.*;


/**
 * This Class is in use for MySQL Database Stuff
 * Please only use this class if you can.
 */
@SuppressWarnings("unused")
public class MySQL {

    public static String host;
    public static String user;
    public static String password;
    public static String database;
    public static String port;
    public static Connection con;
    private static JsonConnection jsonConnection;

    public static void setJsonConnection(JsonConnection jsonConnection) {
        MySQL.jsonConnection = jsonConnection;
    }

    public JsonConnection getJsonConnection() {
        return jsonConnection;
    }

    public MySQL(JsonConnection jsonConnection) {
        MySQL.jsonConnection = jsonConnection;
        host = jsonConnection.getHost();
        user = jsonConnection.getUser();
        password = jsonConnection.getPassword();
        database = jsonConnection.getDatabase();
        port = String.valueOf(jsonConnection.getPort());
    }

    public static String getHost() {
        return host;
    }

    public static void setHost(String host) {
        MySQL.host = host;
    }

    public static String getUser() {
        return user;
    }

    public static void setUser(String user) {
        MySQL.user = user;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        MySQL.password = password;
    }

    public static String getDatabase() {
        return database;
    }

    public static void setDatabase(String database) {
        MySQL.database = database;
    }

    public static String getPort() {
        return port;
    }

    public static void setPort(String port) {
        MySQL.port = port;
    }

    public static Connection getConnection() {
        if (con == null) {
            close();
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?useUnicode=yes&characterEncoding=UTF-8&useSSL=false", user, password);
                return con;
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        } else {
            close();
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?useUnicode=yes&characterEncoding=UTF-8&useSSL=false", user, password);
                return con;
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        return con;
    }

    // connect
    public static void connect() {
        if (con == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?useUnicode=yes&characterEncoding=UTF-8&useSSL=false", user, password);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean isTableExists(String table) {
        try {
            Statement statement = MySQL.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SHOW TABLES LIKE '" + table + "'");
            return rs.next();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            close();
        }
    }

    public static void createTable(String tableName, String... columns) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < columns.length; i++) {
            stringBuilder.append(columns[i]);
            if (i < columns.length - 1) {
                stringBuilder.append(",");
            }
        }
        String builder = stringBuilder.toString();
        try {
            PreparedStatement stmt;
            String sql = "CREATE TABLE IF NOT EXISTS " + tableName + " (ID INT PRIMARY KEY AUTO_INCREMENT," + builder + ",created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP);";
            stmt = MySQL.getConnection().prepareStatement(sql);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    public static void insertData(String table, String data, String... columns) {
        StringBuilder newStringBuilder = new StringBuilder();
        for (int i = 0; i < columns.length; i++) {
            newStringBuilder.append(columns[i]);
            if (i < columns.length - 1) {
                newStringBuilder.append(",");
            }
        }
        String builder2 = "INSERT INTO " + table +
                " (" + newStringBuilder + ")" + " VALUES " + "(" + data + ")";
        try {
            Statement stmt = MySQL.getConnection().createStatement();
            stmt.executeUpdate(builder2);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (MySQL.con != null) {
                MySQL.close();
            }
        }
    }

    public static void updateData(String table, String selected, String data, String where) {
        String sql = "UPDATE " + table + " SET " + selected + " = " + data + " WHERE " + where;
        try {
            Statement stmt = MySQL.getConnection().createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (MySQL.con != null) {
                MySQL.close();
            }
        }
        if (MySQL.con != null) {
            MySQL.close();
        }
    }

    public static void deleteDataInTable(String table, String where) {
        String sql = "DELETE FROM " + table +
                " WHERE " + where;
        try {
            Statement stmt = MySQL.getConnection().createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (MySQL.con != null) {
                MySQL.close();
            }
        }
        if (MySQL.con != null) {
            MySQL.close();
        }
    }

    public static void deleteDataInTable(String table, String where, String and) {
        String sql = "DELETE FROM " + table +
                " WHERE " + where +
                " AND " + and + ";";
        try {
            Statement stmt = MySQL.getConnection().createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (MySQL.con != null) {
                MySQL.close();
            }
        }
        if (MySQL.con != null) {
            MySQL.close();
        }
    }

    public static boolean exists(String table, String column, String data) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * FROM ")
                .append(table)
                .append(" WHERE ")
                .append(column)
                .append(" = '")
                .append(data)
                .append("';");

        try {
            Statement statement = MySQL.getConnection().createStatement();
            String sql = stringBuilder.toString();
            ResultSet res = statement.executeQuery(sql);
            if (res.next()) {
                if (res.getString(column) == null) {
                    return false;
                } else {
                    res.close();
                    statement.close();
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (MySQL.con != null) {
                MySQL.close();
            }
        }
        if (MySQL.con != null) {
            MySQL.close();
        }
        return false;
    }

    public static boolean exists(String table, String column, String data, String and) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * FROM ")
                .append(table)
                .append(" WHERE ")
                .append(column)
                .append(" = '")
                .append(data)
                .append("' AND ")
                .append(and)
                .append(";");

        try {
            Statement statement = MySQL.getConnection().createStatement();
            String sql = stringBuilder.toString();
            ResultSet res = statement.executeQuery(sql);
            if (res.next()) {
                return res.getString(column) != null;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (MySQL.con != null) {
                MySQL.close();
            }
        }
        if (MySQL.con != null) {
            MySQL.close();
        }
        return false;
    }

    public static Object get(String table, String selected, String column, String data) {
        Object o;
        String sql = "SELECT * FROM " +
                table +
                " WHERE " + column + " = '" +
                data +
                "'";
        try {
            Statement statement = MySQL.getConnection().createStatement();
            ResultSet res = statement.executeQuery(sql);
            if (res.next()) {
                o = res.getObject(selected);
                return o;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MySQL.close();
        }
        MySQL.close();
        return null;
    }

}

