package de.framedev.javautils.mysql;

import java.io.File;
import java.sql.*;
import java.util.Objects;

/**
 * This Plugin was Created by FrameDev
 * Package : mysql
 * Date: 07.03.21
 * Project: untitled
 * Copyrighted by FrameDev
 */
@SuppressWarnings({"unused", "ResultOfMethodCallIgnored"})
public class SQLite {

    public static Connection connection;
    private static String fileName;
    private static String path;

    public SQLite(String path, String fileName) {
        if (!new File(path).exists())
            new File(path).mkdirs();
        SQLite.fileName = fileName;
        SQLite.path = path;
    }

    public SQLite(JsonConnection connection) {
        SQLite.fileName = connection.getFileName();
        SQLite.path = connection.getPath();
        if (!new File(SQLite.path).exists())
            new File(SQLite.path).mkdirs();
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
            Statement statement = Objects.requireNonNull(SQLite.connect()).createStatement();
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
                SQLite.connection.close();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }
    }

    /**
     * Erstelle einen Table mit einem Table Name und verschiedene Column
     *
     * @param tableName TableName der erstellt wird
     * @param columns   Kolumm die erstellt werden
     */
    public static void createTable(String tableName, boolean date, String... columns) {
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
            String sql;
            if (date) {
                sql = "CREATE TABLE IF NOT EXISTS " + tableName + " (ID INTEGER PRIMARY KEY AUTOINCREMENT," + builder + ",created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP);";
            } else {
                sql = "CREATE TABLE IF NOT EXISTS " + tableName + " (ID INTEGER PRIMARY KEY AUTOINCREMENT," + builder + ");";
            }
            stmt = Objects.requireNonNull(SQLite.connect()).prepareStatement(sql);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                SQLite.connection.close();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }
        try {
            SQLite.connection.close();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
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
        String sql = "INSERT INTO " + table +
                " (" + newStringBuilder + ")" + " VALUES " + "(" + data + ")";
        try {
            Statement stmt = Objects.requireNonNull(SQLite.connect()).createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                SQLite.connection.close();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }
        try {
            SQLite.connection.close();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public static void updateData(String table, String selected, String data, String where) {
        String sql = "UPDATE " + table + " SET " + selected + " = " + data + " WHERE " + where;
        try {
            Statement stmt = Objects.requireNonNull(SQLite.connect()).createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                SQLite.connection.close();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }
        try {
            SQLite.connection.close();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public static void deleteDataInTable(String table, String where) {
        String sql = "DELETE FROM " + table + " WHERE " + where;
        try {
            Statement stmt = Objects.requireNonNull(SQLite.connect()).createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                SQLite.connection.close();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }
        try {
            SQLite.connection.close();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public static void deleteDataInTable(String table, String where, String and) {
        String sql = "DELETE FROM " +
                table +
                " WHERE " +
                where +
                " AND " +
                and +
                ";";
        try {
            Statement stmt = Objects.requireNonNull(SQLite.connect()).createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                SQLite.connection.close();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }
        try {
            SQLite.connection.close();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
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
            Statement statement = Objects.requireNonNull(SQLite.connect()).createStatement();
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
                SQLite.connection.close();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }
        try {
            SQLite.connection.close();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
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
            Statement statement = Objects.requireNonNull(SQLite.connect()).createStatement();
            String sql = stringBuilder.toString();
            ResultSet res = statement.executeQuery(sql);
            if (res.next()) {
                return res.getString(column) != null;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                SQLite.connection.close();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }
        try {
            SQLite.connection.close();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return false;
    }

    public static Object get(String table, String selected, String column, String data) {
        Object o = null;
        String sql = "SELECT * FROM " +
                table +
                " WHERE " +
                column +
                " = '" +
                data +
                "'";
        try {
            Statement statement = Objects.requireNonNull(SQLite.connect()).createStatement();
            ResultSet res = statement.executeQuery(sql);
            if (res.next()) {
                o = res.getObject(selected);
                return o;
            }
            return o;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            SQLite.close();
        }
        SQLite.close();
        return null;
    }
}
