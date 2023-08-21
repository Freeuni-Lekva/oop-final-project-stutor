package DAO;

import DAO.Interfaces.ChatDAO;
import Model.Message;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlChatDAO implements ChatDAO {
    private final static String DBNAME = "stutor_db";
    private final static String TABLENAME = "chat";

    public SqlChatDAO() throws ClassNotFoundException {

    }

    @Override
    public boolean sendMessage(Message message) throws SQLException {
        StringBuilder code = new StringBuilder();

        code.append("INSERT INTO " + TABLENAME + " (sender, receiver, message) VALUES (?, ?, ? ); ");

        Connection connection = ConnectionPool.getConnection();

        Statement st = connection.createStatement();
        st.execute("USE " + DBNAME + ";\n");
        st.close();

        PreparedStatement statement = connection.prepareStatement(code.toString());

        statement.setString(1, message.getSender());
        statement.setString(2, message.getReceiver());
        statement.setString(3, message.getMessage());

        int check = statement.executeUpdate();

        statement.close();
        ConnectionPool.releaseConnection(connection);

        return check == 1;
    }

    @Override
    public boolean deleteConversation(String first, String second) throws SQLException {
        StringBuilder code = new StringBuilder();

        code.append("DELETE FROM ").append(TABLENAME).append(" WHERE ");
        code.append("(sender = ? and receiver = ?) or (sender = ? and receiver = ?)\n");

        Connection connection = ConnectionPool.getConnection();

        Statement st = connection.createStatement();
        st.execute("USE " + DBNAME + ";\n");
        st.close();

        PreparedStatement statement = connection.prepareStatement(code.toString());

        statement.setString(1, first);
        statement.setString(2, second);
        statement.setString(3, second);
        statement.setString(4, first);

        int check = statement.executeUpdate();

        statement.close();
        ConnectionPool.releaseConnection(connection);

        return check == 1;
    }

    @Override
    public List<String> getUsers(String user) throws SQLException {
        List<String> res = new ArrayList<>();
        StringBuilder code = new StringBuilder();

        code.append("Select * FROM ").append(TABLENAME).append(" WHERE ");
        code.append("sender = ? or receiver = ?\n");
        code.append("order by message_id desc;");

        Connection connection = ConnectionPool.getConnection();

        Statement st = connection.createStatement();
        st.execute("USE " + DBNAME + ";\n");
        st.close();

        PreparedStatement statement = connection.prepareStatement(code.toString());

        statement.setString(1, user);
        statement.setString(2, user);

        ResultSet rs = statement.executeQuery();

        while(rs.next()){
            String user1 = rs.getString("sender");
            String user2 = rs.getString("receiver");
            if(!user.equals(user1) && !res.contains(user1)) res.add(user1);
            if(!user.equals(user2) && !res.contains(user2)) res.add(user2);
        }

        statement.close();
        ConnectionPool.releaseConnection(connection);
        return res;
    }

    @Override
    public List<Message> getConversation(String first, String second) throws SQLException {
        List<Message> res = new ArrayList<>();
        StringBuilder code = new StringBuilder();

        code.append("Select * FROM ").append(TABLENAME).append(" WHERE ");
        code.append("(sender = ? and receiver = ?) or (sender = ? and receiver = ?)\n");
        code.append("order by message_id desc;");

        Connection connection = ConnectionPool.getConnection();

        Statement st = connection.createStatement();
        st.execute("USE " + DBNAME + ";\n");
        st.close();

        PreparedStatement statement = connection.prepareStatement(code.toString());

        statement.setString(1, first);
        statement.setString(2, second);
        statement.setString(3, second);
        statement.setString(4, first);

        ResultSet rs = statement.executeQuery();

        while(rs.next()){
            res.add(new Message(rs.getString("sender"),
                    rs.getString("receiver"), rs.getString("message")));
            res.get(res.size() - 1).setMessage_id(rs.getInt("message_id"));
        }

        statement.close();
        ConnectionPool.releaseConnection(connection);

        return res;
    }

    public void deleteChat() throws SQLException {
        Connection connection = ConnectionPool.getConnection();
        Statement statement = connection.createStatement();

        StringBuilder code = new StringBuilder();

        statement.execute("USE " + DBNAME + ";\n");

        code.append("DELETE FROM ").append(TABLENAME).append(";");

        statement.executeUpdate(code.toString());

        statement.close();
        ConnectionPool.releaseConnection(connection);
    }
}
