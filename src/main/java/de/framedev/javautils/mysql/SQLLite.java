package de.framedev.javautils.mysql;

import java.sql.*;

/**
 * This Plugin was Created by FrameDev
 * Package : mysql
 * Date: 07.03.21
 * Project: untitled
 * Copyrighted by FrameDev
 */

public class SQLLite {

    public static Connection connection;
    private static String fileName;
    private static String path;

    public SQLLite(String path, String fileName) {
        SQLLite.fileName = fileName;
        SQLLite.path = path;
    }

    public static Connection connect() {
        Connection conn = null;
        try {
            // db parameters
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:" + path + "/" + fileName + ".db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            connection = conn;
            return conn;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static boolean isTableExists(String table) {
        try {
            Statement statement = SQLLite.connect().createStatement();
            ResultSet rs = statement.executeQuery("SELECT \n" +
                    "    name\n" +
                    "FROM \n" +
                    "    sqlite_master \n" +
                    "WHERE \n" +
                    "    type ='table' AND \n" +
                    "    name NOT LIKE 'sqlite_%';");
            while (rs.next()) {
                if (rs.getString("name").equalsIgnoreCase(table))
                    return true;
            }
            return false;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            try {
                SQLLite.connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    /**
     * Erstelle einen Table mit einem Table Name und verschiedene Column
     *
     * @param tablename TableName der erstellt wird
     * @param columns   Kolumm die erstellt werden
     */
    public static void createTable(String tablename, boolean date, String... columns) {
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
            if (date) {
                String sql = "CREATE TABLE IF NOT EXISTS " + tablename + " (ID INTEGER PRIMARY KEY AUTO_INCREMENT," + builder + ",created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP);";
                stmt = SQLLite.connect().prepareStatement(sql);
            } else {
                String sql = "CREATE TABLE IF NOT EXISTS " + tablename + " (ID INTEGER PRIMARY KEY AUTOINCREMENT," + builder + ");";
                stmt = SQLLite.connect().prepareStatement(sql);
            }
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                SQLLite.connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        try {
            SQLLite.connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
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
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("INSERT INTO " + table);
        stringBuilder.append(" (").append(newStringBuilder.toString()).append(")").append(" VALUES ").append("(").append(data).append(")");
        String builder2 = stringBuilder.toString();
        try {
            Statement stmt = SQLLite.connect().createStatement();
            stmt.executeUpdate(builder2);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                SQLLite.connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        try {
            SQLLite.connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void updateData(String table, String selected, String data, String where) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("UPDATE " + table + " SET ").append(selected + " = " + data).append(" WHERE " + where);
        String sql = stringBuilder.toString();
        try {
            Statement stmt = SQLLite.connect().createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                SQLLite.connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        try {
            SQLLite.connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void deleteDataInTable(String table, String where) {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM " + table)
                .append(" WHERE " + where);
        String sql = sb.toString();
        try {
            Statement stmt = SQLLite.connect().createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                SQLLite.connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        try {
            SQLLite.connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void deleteDataInTable(String table, String where, String and) {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM " + table)
                .append(" WHERE " + where)
                .append(" AND " + and + ";");
        String sql = sb.toString();
        try {
            Statement stmt = SQLLite.connect().createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                SQLLite.connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        try {
            SQLLite.connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static boolean exists(String table, String column, String data) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * FROM ")
                .append(table)
                .append(" WHERE ")
                .append(column)
                .append(" = '" + data + "';");

        try {
            Statement statement = SQLLite.connect().createStatement();
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
            try {
                SQLLite.connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        try {
            SQLLite.connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public static boolean exists(String table, String column, String data, String and) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * FROM ")
                .append(table)
                .append(" WHERE ")
                .append(column)
                .append(" = '" + data + "' AND " + and + ";");

        try {
            Statement statement = SQLLite.connect().createStatement();
            String sql = stringBuilder.toString();
            ResultSet res = statement.executeQuery(sql);
            if (res.next()) {
                if (res.getString(column) == null) {
                    return false;
                }
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                SQLLite.connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        try {
            SQLLite.connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }


}
