package DAO;

import DAO.Interfaces.AdminDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlAdminDAO implements AdminDAO {
    private final static String DBNAME = "stutor_db";
    private final static String TABLENAME = "admins";

    public SqlAdminDAO(){

    }
    @Override
    public boolean addAdmin(String username) throws SQLException {
        StringBuilder code = new StringBuilder();

        code.append("INSERT INTO " + TABLENAME + " (adminName) VALUES (? ); ");

        Connection connection = ConnectionPool.getConnection();

        Statement st = connection.createStatement();
        st.execute("USE " + DBNAME + ";\n");
        st.close();

        PreparedStatement statement = connection.prepareStatement(code.toString());

        statement.setString(1, username);

        int check;

        try{
            check = statement.executeUpdate();
        }catch (SQLException e){
            return false;
        }

        statement.close();
        ConnectionPool.releaseConnection(connection);

        return check == 1;
    }

    @Override
    public boolean removeAdmin(String username) throws SQLException {
        Connection connection = ConnectionPool.getConnection();
        Statement statement = connection.createStatement();

        statement.execute("USE " + DBNAME + ";\n");

        StringBuilder code = new StringBuilder();

        code.append("DELETE FROM ").append(TABLENAME).append(" WHERE ");
        code.append("adminName = '").append(username).append("';");

        int check = statement.executeUpdate(code.toString());

        statement.close();
        ConnectionPool.releaseConnection(connection);

        return true;
    }

    @Override
    public boolean isAdmin(String username) throws SQLException {
        boolean res = false;

        Connection connection = ConnectionPool.getConnection();
        Statement statement = connection.createStatement();

        statement.execute("USE " + DBNAME + ";\n");

        StringBuilder code = new StringBuilder();

        code.append("select * from ").append(TABLENAME);
        code.append(" where adminName = '").append(username).append("';");

        ResultSet rs = statement.executeQuery(code.toString());

        while(rs.next()){
            res = true;
        }

        statement.close();
        ConnectionPool.releaseConnection(connection);

        return res;
    }

    @Override
    public List<String> getAllAdmins() throws SQLException {
        List<String> res = new ArrayList<>();

        Connection connection = ConnectionPool.getConnection();
        Statement statement = connection.createStatement();

        StringBuilder code = new StringBuilder();

        statement.execute("USE " + DBNAME + ";\n");

        code.append("select * from ").append(TABLENAME).append(";");

        ResultSet rs = statement.executeQuery(code.toString());

        while(rs.next()){
            res.add(rs.getString("adminName"));
        }

        statement.close();
        ConnectionPool.releaseConnection(connection);

        return res;
    }

    @Override
    public void clearAdmins() throws SQLException {
        Connection connection = ConnectionPool.getConnection();
        Statement statement = connection.createStatement();

        StringBuilder code = new StringBuilder();

        statement.execute("USE " + DBNAME + ";\n");

        code.append("DELETE FROM admins;");

        statement.executeUpdate(code.toString());

        statement.close();
        ConnectionPool.releaseConnection(connection);
    }
}
